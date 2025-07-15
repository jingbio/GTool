package com.way.gtool.common.config;

import io.micrometer.observation.ObservationRegistry;
import org.springframework.ai.chat.observation.ChatModelObservationConvention;
import org.springframework.ai.model.SpringAIModelProperties;
import org.springframework.ai.model.SpringAIModels;
import org.springframework.ai.model.openai.autoconfigure.*;
import org.springframework.ai.model.tool.DefaultToolExecutionEligibilityPredicate;
import org.springframework.ai.model.tool.ToolCallingManager;
import org.springframework.ai.model.tool.ToolExecutionEligibilityPredicate;
import org.springframework.ai.model.tool.autoconfigure.ToolCallingAutoConfiguration;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.retry.autoconfigure.SpringAiRetryAutoConfiguration;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.client.RestClientAutoConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.ai.model.openai.autoconfigure.OpenAIAutoConfigurationUtil.resolveConnectionProperties;

/**
 * @author Jingway
 * @date 2025/7/14 16:03
 * @description: 重写 OpenAiApi 的配置，使用随机的 appKey
 */
@Primary // 确保这个配置在其他 OpenAiApi 配置之前加载
@AutoConfiguration(after = { RestClientAutoConfiguration.class, WebClientAutoConfiguration.class,
        SpringAiRetryAutoConfiguration.class, ToolCallingAutoConfiguration.class })
@ConditionalOnClass(OpenAiApi.class)
@EnableConfigurationProperties({ OpenAiConnectionProperties.class, OpenAiChatProperties.class })
@ConditionalOnProperty(name = SpringAIModelProperties.CHAT_MODEL, havingValue = SpringAIModels.OPENAI,
        matchIfMissing = true)
@ImportAutoConfiguration(classes = { SpringAiRetryAutoConfiguration.class, RestClientAutoConfiguration.class,
        WebClientAutoConfiguration.class, ToolCallingAutoConfiguration.class })
public class OpenAiAppKeyAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public OpenAiChatModel openAiChatModel(OpenAiConnectionProperties commonProperties,
       OpenAiChatProperties chatProperties, ObjectProvider<RestClient.Builder> restClientBuilderProvider,
       ObjectProvider<WebClient.Builder> webClientBuilderProvider, ToolCallingManager toolCallingManager,
       RetryTemplate retryTemplate, ResponseErrorHandler responseErrorHandler,
       ObjectProvider<ObservationRegistry> observationRegistry,
       ObjectProvider<ChatModelObservationConvention> observationConvention,
       ObjectProvider<ToolExecutionEligibilityPredicate> openAiToolExecutionEligibilityPredicate) {

        var openAiApi = openAiApi(chatProperties, commonProperties, responseErrorHandler, "chat");

        var chatModel = OpenAiChatModel.builder()
            .openAiApi(openAiApi)
            .defaultOptions(chatProperties.getOptions())
            .toolCallingManager(toolCallingManager)
            .toolExecutionEligibilityPredicate(openAiToolExecutionEligibilityPredicate.getIfUnique(DefaultToolExecutionEligibilityPredicate::new))
            .retryTemplate(retryTemplate)
            .observationRegistry(observationRegistry.getIfUnique(() -> ObservationRegistry.NOOP))
            .build();

        observationConvention.ifAvailable(chatModel::setObservationConvention);

        return chatModel;
    }

    public OpenAiApi openAiApi(OpenAiChatProperties chatProperties, OpenAiConnectionProperties commonProperties,
       ResponseErrorHandler responseErrorHandler, String modelType) {

        OpenAIAutoConfigurationUtil.ResolvedConnectionProperties resolved = resolveConnectionProperties(
            commonProperties, chatProperties, modelType);

        var rotatingKey = new RotatingApiKey(resolved.apiKey());

        var restClient = RotatingApiKey.buildRestClient(resolved.baseUrl(), rotatingKey, resolved.headers(), responseErrorHandler);

        var webClient = RotatingApiKey.buildWebClient(resolved.baseUrl(), rotatingKey, resolved.headers());

        return OpenAiApi.builder()
            .baseUrl(resolved.baseUrl())
            .apiKey(rotatingKey)
            .headers(resolved.headers())
            .completionsPath(chatProperties.getCompletionsPath())
            .embeddingsPath(OpenAiEmbeddingProperties.DEFAULT_EMBEDDINGS_PATH)
            .restClientBuilder(restClient.mutate())
            .webClientBuilder(webClient.mutate())
            .responseErrorHandler(responseErrorHandler)
            .build();
    }
}