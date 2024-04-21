package com.io25.tiloproject.services.impl;

import com.io25.tiloproject.dto.YogaServiceDTO;
import com.io25.tiloproject.mappers.YogaServiceMapper;
import com.io25.tiloproject.services.ImageService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.io25.tiloproject.model.YogaService;
import com.io25.tiloproject.services.YogaServiceService;
import com.io25.tiloproject.repository.YogaServicesRepository;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class YogaServiceServiceImpl implements YogaServiceService {
    private final YogaServicesRepository yogaServicesRepository;
    private final ImageService imageService;


    @Override
    public List<YogaService> getAllServices() {
        return yogaServicesRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        yogaServicesRepository.findById(id).ifPresent(
                service -> {
                    yogaServicesRepository.delete(service);
                    imageService.removeImage(getServiceImageName(service));
                }
        );
    }

    @Transactional
    @Override
    public YogaService saveNewService(YogaServiceDTO yogaServiceDTO) throws IOException {
        YogaService service = YogaServiceMapper.INSTANCE.dtoToEntity(yogaServiceDTO);
        service.setImgName(getServiceImageName(service));
        yogaServicesRepository.save(service);
        imageService.saveImage(getServiceImageName(service),yogaServiceDTO.getImg().getBytes());
        return service;
    }
    private String getServiceImageName(YogaService service){
        String fileName = service.getImgName();
        String extension = fileName.substring(fileName.lastIndexOf('.'));
        return "service_"+service.getName()+extension;
    }

}
