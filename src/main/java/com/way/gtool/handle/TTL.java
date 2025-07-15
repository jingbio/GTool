package com.way.gtool.handle;

import com.way.gtool.common.utils.Result;
import com.way.gtool.common.utils.SpringContextUtil;
import com.way.gtool.domain.IStrategy;
import com.way.gtool.domain.type.Operate;
import com.way.gtool.domain.vo.Op;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;

import java.util.List;

/**
 * @author Jingway
 * @date 2025/7/8 17:04
 * @description: 音频
 */
@Deprecated
public class TTL implements IStrategy {

    private Result toVoice(String data){
        OpenAiAudioSpeechOptions speechOptions = OpenAiAudioSpeechOptions.builder()
            .model("gemini-2.5-flash-preview-tts")
            .voice(OpenAiAudioApi.SpeechRequest.Voice.ALLOY)
            .responseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
            .speed(1.0f)
            .build();
        SpeechPrompt speechPrompt = new SpeechPrompt(data, speechOptions);
        SpeechResponse response = SpringContextUtil.getBean(OpenAiAudioSpeechModel.class).call(speechPrompt);
        return Result.get(200, "转换成功", response);
    }

    @Override
    public List<Op> getOps() {
        return List.of(
            new Op("文字转语音", Operate.TEXTTOVOICE)
        );
    }

    @Override
    public Result execute(Operate op, String data) {
        if (Operate.TEXTTOVOICE ==op) {
            return this.toVoice(data);
        } else {
            return Result.get(500, "操作失败! 未实现的方法",null);
        }
    }
}
