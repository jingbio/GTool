package com.jason.gtool.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JingWei Guo
 * @date 2024/12/28 13:34
 * @desciption:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Funcs {
    private String name;
    private int op;
}
