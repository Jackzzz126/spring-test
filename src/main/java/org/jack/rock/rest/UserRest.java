package org.jack.rock.rest;

import org.h2.engine.User;
import org.jack.rock.repository.UserRepository;
import org.jack.rock.entity.UserEntity;
import org.jack.rock.util.Result;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

/**
 * User
 *
 * @author zhengzhe17
 * @date 2020/7/14
 */
@RestController
@RequestMapping(value = "/user", produces = "application/json")
public class UserRest extends CrudRest<UserEntity> {
    public UserRest(UserRepository userRepository) {
        super.repository = userRepository;
    }

    @PostMapping("/login")
    public Result<String> login(@Valid @RequestBody UserEntity reqUserEntity, BindingResult br, HttpSession httpSession) {
        if(reqUserEntity.getName() == null || reqUserEntity.getPassWord() == null) {
            return Result.badRequest();
        }

        Optional<UserEntity> userEntityFound = ((UserRepository)repository).findByName(reqUserEntity.getName());
        if(!userEntityFound.isPresent()) {
            return Result.error("用户名密码错误");
        }
        if(userEntityFound.get().getPassWord().equals(reqUserEntity.getPassWord())) {
            httpSession.setAttribute(httpSession.getId(), userEntityFound.get().getId());
            return Result.success();
        } else {
            return Result.error("用户名密码错误");
        }
    }

    @GetMapping("/logout")
    public Result<String> logout(HttpSession httpSession) {
        httpSession.removeAttribute(httpSession.getId());
        return Result.success();
    }
}
