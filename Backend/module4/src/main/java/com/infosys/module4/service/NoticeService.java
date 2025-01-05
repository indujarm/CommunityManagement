package com.infosys.module4.service;



import com.infosys.module4.dto.NoticeDto;
import com.infosys.module4.exception.NoticeException;
import com.infosys.module4.model.Notice;
import com.infosys.module4.response.NoticeResponse;

import java.util.List;

public interface NoticeService {
    public NoticeResponse addNotice(String jwt, NoticeDto noticeDto) throws NoticeException;
    public List<Notice> getAllNotices() throws NoticeException;
    public NoticeResponse updateNotice(Long noticeId,NoticeDto noticeDto) throws NoticeException;
    public NoticeResponse deleteNotice(Long noticeId) throws NoticeException;
    public Notice getNoticeById(Long noticeId) throws NoticeException;
}
