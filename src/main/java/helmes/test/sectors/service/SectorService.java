package helmes.test.sectors.service;

import helmes.test.sectors.model.Sector;
import helmes.test.sectors.model.SectorData;
import helmes.test.sectors.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectorService {

    @Autowired
    private SectorRepository sectorRepository;

    public List<SectorData> getAllSectors() {
        List<Sector> allSectors = new ArrayList<>();
        sectorRepository.findAll()
                .forEach(allSectors::add);
        return sortSectors(allSectors);
    }

    public List<SectorData> sortSectors(List<Sector> sectors) {
        List<SectorData> sectorDataList = new ArrayList<>();
        for(Sector sector : sectors) {
            sectorDataList.add(createSectorData(sector));
        }
        /** Map children sectors to parents */
        HashMap<SectorData, List<SectorData>> sectorMappedChildren= new HashMap<>();
        for(SectorData sectorData : sectorDataList) {
            ArrayList<SectorData> childrenIds = findSectorChildren(sectorData, sectorDataList);
            sectorMappedChildren.put(sectorData, childrenIds);
        }
        return addChildrenSectorsToParents(sectorDataList, sectorMappedChildren);
    }

    public  ArrayList<SectorData> findSectorChildren(SectorData sectorData, List<SectorData> sectors) {
        ArrayList<SectorData> childrenIds = new ArrayList();
        for(SectorData childSector : sectors) {
            if(childSector.getParentSectorName().equals(sectorData.getName())) {
                childrenIds.add(childSector);
            }
        }
        return childrenIds;
    };

    public List<SectorData> addChildrenSectorsToParents(List<SectorData> sectors, HashMap<SectorData, List<SectorData>> sectorMappedChildrenIds) {
        List<SectorData> mainSectors = sectors.stream().filter(s -> s.getParentSectorName().equals("")).collect(Collectors.toList());
        for(SectorData mainSector: mainSectors) {
            mainSector.setChildren(getChildrenSectors(sectorMappedChildrenIds, mainSector));
        }
        return mainSectors;

    }

    public List<SectorData> getChildrenSectors(HashMap<SectorData, List<SectorData>> sectorMappedChildrenIds, SectorData parentSector) {
        List<SectorData> childrenSectorsList = sectorMappedChildrenIds.get(parentSector);
        List<SectorData> childSectors = new ArrayList<>();
        for(SectorData childSector : childrenSectorsList) {
            childSector.setChildren(getChildrenSectors(sectorMappedChildrenIds, childSector));
            childSectors.add(childSector);
        }
        return childSectors;
    }

    public SectorData createSectorData(Sector sector) {
        List<SectorData> children = new ArrayList<>();
        return new SectorData(sector.getId(), sector.getName(), sector.getValue(), sector.getParentSectorName(), children);
    }
}
