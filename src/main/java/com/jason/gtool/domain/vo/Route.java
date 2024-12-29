package com.jason.gtool.domain.vo;

import com.jason.gtool.domain.type.RouteEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JingWei Guo
 * @date 2024/12/28 13:12
 * @desciption:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Route {
    private String name;
    private RouteEnum value;
}
