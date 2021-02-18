package org.jack.rock.rest;

import org.jack.rock.entity.BaseEntity;
import org.jack.rock.util.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;

/**
 * BaseRest
 *
 * @author zhengzhe17
 * @CreateTime 2020/8/27
 */
public abstract class CrudRest<T extends BaseEntity> {
    protected JpaRepository<T, Long> repository;

    @PostMapping("/create")
    public Result<Long> create(@Valid @RequestBody T obj, BindingResult br) {
        if(br.hasErrors()) {
            return Result.badRequest();
        }
        if(obj.getId() != null) {
            return Result.error("新建对象不能有ID字段");
        }

        repository.save(obj);

        return Result.success(obj.getId());
    }

    @PostMapping("/update")
    public Result<String> update(@Valid @RequestBody T obj, BindingResult br) {
        if(br.hasErrors()) {
            return Result.badRequest();
        }

        Optional<T> objFound = repository.findById(obj.getId());
        if(!objFound.isPresent()) {
            return Result.error("对象不存在");
        }

        repository.save(obj);

        return Result.success("更新对象成功");
    }

    @GetMapping("/get/{id}")
    public Result<T> get(@PathVariable("id") Long id) {
        Optional<T> objFound = repository.findById(id);
        if(!objFound.isPresent()) {
            return Result.error("对象不存在");
        } else {
            return Result.success(objFound.get());
        }
    }

    @GetMapping("/list")
    public Result<Iterable<T>> list(@RequestParam(value = "ps", defaultValue = "10") Long ps,
                                        @RequestParam(value = "pn", defaultValue = "0") Long pn
    ) {
        Pageable pageable = PageRequest.of(pn.intValue(), ps.intValue(), Sort.Direction.ASC, "id");
        Page<T> objList = repository.findAll(pageable);

        return Result.success(objList);
    }

    @PostMapping("/del/{id}")
    public Result<String> del(@PathVariable("id") Long id) {
        Optional<T> objFound = repository.findById(id);
        if(!objFound.isPresent()) {
            return Result.error("对象不存在");
        }
        repository.deleteById(id);

        return Result.success("删除对象成功");
    }
}
