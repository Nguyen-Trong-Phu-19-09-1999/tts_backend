package com.mycompany.service.DI;


import com.mycompany.dto.BillDto;
import com.mycompany.model.Bill;
import com.mycompany.model.Response;
import com.mycompany.repository.IBill;
import com.mycompany.service.itf.IBillService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BillService implements IBillService {
    @Autowired
    private IBill iBill;

    @Override // Trả qua DTO
    public List<BillDto> findAll() {
        // Tạo list mới
        List<BillDto> list = new ArrayList<>();
        // Tìm all gán vào list
        List<Bill> billList = iBill.findAll();
        //   KDL  TÊN : TẬP HỢP LẶP
        for (Bill bill: billList) {
            // Tạo đối tượng mới
            BillDto billDto = new BillDto();
            // Lấy dữ liệu Entity sang DTO
            BeanUtils.copyProperties(bill,billDto);
            // Add list
            list.add(billDto);
        }
        return list;
    }

    @Override
    public ResponseEntity findById(Long id) {
        // Tạo đối tượng mới
        BillDto billDto = new BillDto();

        Bill optionalBill = iBill.findBillById(id);
        if (optionalBill != null) {

            // Lấy dữ liệu Entity sang DTO
            BeanUtils.copyProperties(optionalBill, billDto);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response("Ok", "Tim thanh cong", billDto)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new Response("Flase", "Khong co ID nay", billDto)
            );
        }
    }

    @Override
    public ResponseEntity save(BillDto bill) {
        // Tao MDMP
        ModelMapper modelMapper = new ModelMapper();
        // DTO --> Entity
        Bill bill1 = modelMapper.map(bill, Bill.class);
        // Entity --> DTO
        BillDto billDto = modelMapper.map(bill1, BillDto.class);
        if (bill.getName() == "") {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new Response("flase", "Khong dc de trong", billDto)
            );
        } else {
            iBill.save(bill1);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response("ok", "Them thành công", billDto)
            );
        }
    }

    @Override
    public ResponseEntity delete(Long id) {
        Optional<Bill> billEntity = iBill.findById(id);
        if (billEntity.isPresent()) {

            iBill.delete(iBill.findById(id).get());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response("ok", "Xóa thành công", billEntity)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new Response("flase", "khong ton tai", billEntity)
            );
        }
    }
}
