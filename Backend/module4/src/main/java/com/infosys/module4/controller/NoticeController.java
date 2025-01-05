package com.infosys.module4.controller;

import com.infosys.module4.dto.NoticeDto;
import com.infosys.module4.exception.NoticeException;
import com.infosys.module4.model.Notice;
import com.infosys.module4.response.NoticeResponse;
import com.infosys.module4.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NoticeController {
    @Autowired
    NoticeService noticeService;
    @PostMapping("/addNotice")
    public NoticeResponse addNotice(@RequestHeader("Authorization") String jwt, @RequestBody NoticeDto noticeDto) throws NoticeException {
        return noticeService.addNotice(jwt, noticeDto);
    }

    @GetMapping("/getAllNotices")
    public List<Notice> getAllNotices(@RequestHeader("Authorization") String jwt) throws NoticeException{
        return noticeService.getAllNotices();
    }

    @PutMapping("updateNotice/{noticeId}")
    public NoticeResponse updateNotice(@RequestHeader("Authorization") String jwt,@RequestBody NoticeDto noticeDto,@PathVariable Long noticeId){
        return noticeService.updateNotice(noticeId,noticeDto);
    }

    @DeleteMapping("/deleteNotice/{noticeId}")
    public NoticeResponse deleteNotice(@RequestHeader("Authorization") String jwt,@PathVariable Long noticeId){
        return noticeService.deleteNotice(noticeId);
    }

    @GetMapping("/getNoticeById/{noticeId}")
    public Notice getNoticeByNoticeId(@RequestHeader("Authorization") String jwt,@PathVariable Long noticeId){
        return noticeService.getNoticeById(noticeId);
    }
}
