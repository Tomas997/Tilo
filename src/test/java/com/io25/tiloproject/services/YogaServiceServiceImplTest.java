package com.io25.tiloproject.services;

import com.io25.tiloproject.model.YogaService;
import com.io25.tiloproject.repository.YogaServicesRepository;
import com.io25.tiloproject.services.impl.YogaServiceServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class YogaServiceServiceImplTest {

    @Mock
    private YogaServicesRepository yogaServicesRepository;

    @Mock
    private ImageService imageService;

    @InjectMocks
    private YogaServiceServiceImpl yogaService;

    public YogaServiceServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllServices() {
        List<YogaService> services = Collections.singletonList(new YogaService());
        when(yogaServicesRepository.findAll()).thenReturn(services);

        List<YogaService> result = yogaService.getAllServices();

        assertEquals(services, result);
    }

//    @Test
//    void testDeleteById() {
//        Long id = 1L;
//        YogaService service = new YogaService();
//        service.setId(id);
//        when(yogaServicesRepository.findById(id)).thenReturn(Optional.of(service));
//
//        yogaService.deleteById(id);
//
//        verify(yogaServicesRepository, times(1)).delete(service);
//        verify(imageService, times(1)).removeImage(anyString());
//    }
//
//    @Test
//    void testSaveNewService() throws IOException {
//        YogaServiceDTO dto = new YogaServiceDTO();
//        dto.setName("Test Service");
//        dto.setImg(new MockMultipartFile("image.jpg", new byte[0]));
//
//        YogaService service = new YogaService();
//        service.setName("Test Service");
//        service.setId(1L); // Setting an id for the service
//
//        when(yogaServicesRepository.save(any(YogaService.class))).thenReturn(service);
//
//        YogaService result = yogaService.saveNewService(dto);
//
//        assertEquals(service, result);
//        verify(yogaServicesRepository, times(1)).save(any(YogaService.class));
//        verify(imageService, times(1)).saveImage(anyString(), any(byte[].class));
//    }
}
