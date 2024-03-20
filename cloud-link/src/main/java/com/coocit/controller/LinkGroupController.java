package com.coocit.controller;


import com.coocit.controller.request.LinkGroupAddRequest;
import com.coocit.controller.request.LinkGroupUpdateRequest;
import com.coocit.enums.BizCodeEnum;
import com.coocit.service.LinkGroupService;
import com.coocit.utils.JsonData;
import com.coocit.vo.LinkGroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Coocit
 * @since 2024-03-20
 */
@RestController
@RequestMapping("/api/group/v1")
public class LinkGroupController {

    @Autowired
    private LinkGroupService linkGroupService;

    /**
     * 创建分组
     * @param addRequest
     * @return
     */
    @PostMapping("/add")
    public JsonData add(@RequestBody LinkGroupAddRequest addRequest){

        int rows = linkGroupService.add(addRequest);

        return rows == 1 ? JsonData.buildSuccess():JsonData.buildResult(BizCodeEnum.GROUP_ADD_FAIL);

    }


    /**
     * 根据id删除分组
     * @param groupId
     * @return
     */
    @DeleteMapping("/del/{group_id}")
    public JsonData del(@PathVariable("group_id") Long groupId){

        int rows = linkGroupService.del(groupId);
        return rows == 1 ? JsonData.buildSuccess():JsonData.buildResult(BizCodeEnum.GROUP_NOT_EXIST);

    }


    /**
     * 根据id找详情
     * @param groupId
     * @return
     */
    @GetMapping("detail/{group_id}")
    public JsonData detail(@PathVariable("group_id") Long groupId){

        LinkGroupVO linkGroupVO = linkGroupService.detail(groupId);
        return JsonData.buildSuccess(linkGroupVO);

    }


    /**
     * 列出用户全部分组
     * @return
     */
    @GetMapping("list")
    public JsonData findUserAllLinkGroup(){

        List<LinkGroupVO> list = linkGroupService.listAllGroup();

        return JsonData.buildSuccess(list);

    }



    /**
     * 列出用户全部分组
     * @return
     */
    @PutMapping("update")
    public JsonData update(@RequestBody LinkGroupUpdateRequest request){


        int rows = linkGroupService.updateById(request);
        return rows == 1 ? JsonData.buildSuccess():JsonData.buildResult(BizCodeEnum.GROUP_OPER_FAIL);

    }


}

