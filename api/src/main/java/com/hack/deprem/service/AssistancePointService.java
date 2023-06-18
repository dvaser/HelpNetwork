package com.hack.deprem.service;

import com.hack.deprem.dto.AssistancePointDto;
import com.hack.deprem.dto.request.CreateAssistancePointRequest;
import com.hack.deprem.model.AssistancePoint;
import com.hack.deprem.model.Product;
import com.hack.deprem.repository.AssistancePointRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssistancePointService {
    private final AssistancePointRepository assistancePointRepository;

    public AssistancePointService(AssistancePointRepository assistancePointRepository) {
        this.assistancePointRepository = assistancePointRepository;
    }

    public void createAssistancePoint(CreateAssistancePointRequest request){
        AssistancePoint assistancePoint = new AssistancePoint(request.location());
        assistancePointRepository.save(assistancePoint);
    }

    public List<AssistancePointDto> getPoints(){
        return assistancePointRepository.findAll().stream().map(AssistancePointDto::convert).toList();
    }

    public AssistancePointDto getPoint(String location){
        return AssistancePointDto.convert(assistancePointRepository.getAssistancePointByLocation(location));
    }

    protected void updateOnRoad(String location, Product product){
        if(assistancePointRepository.existsByLocation(location)) {
            AssistancePoint assistancePoint = assistancePointRepository.getAssistancePointByLocation(location);
            List<Product> onRoad = assistancePoint.getOnRoad();
            onRoad.add(product);
            AssistancePoint updatedPoint = new AssistancePoint(assistancePoint.getUuid(),
                    assistancePoint.getLocation(), assistancePoint.getStock(), onRoad, assistancePoint.getNeed());
            assistancePointRepository.save(updatedPoint);
        }
    }

    protected void updateNeed(String location, Product product){
        if(assistancePointRepository.existsByLocation(location)){
            AssistancePoint assistancePoint = assistancePointRepository.getAssistancePointByLocation(location);
            List<Product> need = assistancePoint.getNeed();
            need.add(product);
            AssistancePoint updatedPoint = new AssistancePoint(assistancePoint.getUuid(),
                    assistancePoint.getLocation(),assistancePoint.getStock(), assistancePoint.getOnRoad(), need);
            assistancePointRepository.save(updatedPoint);
        }
    }

    protected void updateStock(String location, Product product){
        AssistancePoint assistancePoint = assistancePointRepository.getAssistancePointByLocation(location);
        List<Product> stock = assistancePoint.getStock();
        stock.add(product);
        AssistancePoint updatedPoint = new AssistancePoint(assistancePoint.getUuid(),
                assistancePoint.getLocation(),stock,assistancePoint.getStock(), assistancePoint.getNeed());
        assistancePointRepository.save(updatedPoint);
    }
}
