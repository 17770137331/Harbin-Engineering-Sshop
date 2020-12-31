package org.wumbuk.service.impl;

import org.wumbuk.dao.RecordMapper;
import org.wumbuk.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther PeihaoYang
 * @date 2020/10/3 - 11:43
 */

@Service("recordService")
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordMapper recordMapper;
    @Override
    public int delete(String phone) {
        return recordMapper.delete(phone);
    }
}
