package com.hm.digital.common.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hm.digital.common.biz.BaseRepository;
import com.hm.digital.common.utils.ResultData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseController<B extends BaseRepository, E> {
  @Autowired
  protected HttpServletRequest request;
  @Autowired
  protected B baseBiz;

  /**
   * 添加一条记录
   *
   * @param entity
   * @return
   */
  @RequestMapping(value = "", method = RequestMethod.POST)
  @ResponseBody
  public ResultData<Object> add(@RequestBody E entity) {
    Object object = baseBiz.save(entity);
    return ResultData.success(object);
  }

  /**
   * 根据id或作业编号查询一条记录
   *
   * @param id
   * @return
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public ResultData<E> get(@PathVariable String id) {
    return ResultData.success((E) baseBiz.findById(id));
  }

  /**
   * 根据id或作业编号更新一条记录
   *
   * @param id
   * @param entity
   * @return
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  @ResponseBody
  public ResultData<E> update(@PathVariable String id,
      @RequestBody E entity) {
    baseBiz.saveAndFlush(entity);
    return ResultData.success(entity);
  }

  /**
   * 根据id删除
   *
   * @param id
   * @return
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public ResultData<E> remove(@PathVariable String id) {
    baseBiz.deleteById(id);
    return ResultData.success();
  }
}

