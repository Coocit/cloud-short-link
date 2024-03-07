package com.coocit.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Coocit
 * @date: 2024/3/7
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrafficPageRequest {

    private  int page;

    private int size;

}
