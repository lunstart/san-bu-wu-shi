package com.sky.controller.user;

import com.sky.context.BaseContext;
import com.sky.dto.AddressBookDTO;
import com.sky.entity.AddressBook;
import com.sky.result.Result;
import com.sky.service.AddressBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/addressBook")
@Api(tags = "C端地址簿接口")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    /**
     * 查询当前登录用户的所有地址信息
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查询当前登录用户的地址信息")
    public Result<AddressBook> getByUserId() {
        AddressBook addressBook = new AddressBook();
        addressBook.setUserId(BaseContext.getCurrentId());
        //TODO
        //addressBook.setUserId(1L);
        List<AddressBook> addressBooks = addressBookService.list(addressBook);
        if (addressBooks.size() <= 0 || addressBooks == null) {
            return null;
        }
        addressBook = addressBooks.get(0);
        return Result.success(addressBook);
    }

    /**
     * 更改地址
     *
     * @param addressBookDTO
     * @return
     */
    @PostMapping
    @ApiOperation("更改地址")
    public Result save(@RequestBody AddressBookDTO addressBookDTO) {
        AddressBook addressBook = new AddressBook();
        BeanUtils.copyProperties(addressBookDTO,addressBook);
        addressBookService.save(addressBook);
        return Result.success();
    }

}
