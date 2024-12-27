package com.jason.gtool.domain.req;

import com.jason.gtool.domain.type.RouteEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JingWei Guo
 * @date 2024/12/26 15:29
 * @desciption: 请求入参
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GDoPram {
    private RouteEnum route;
    private int op;
    private String data;
}
