package helmes.test.sectors.service;

import helmes.test.sectors.model.Sector;
import helmes.test.sectors.model.SectorData;
import helmes.test.sectors.repository.SectorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SectorServiceTest {
    @Mock
    SectorRepository sectorRepository;

    @InjectMocks
    SectorService sectorService;

    List<Sector> sectors = new ArrayList<>();

     @Before
     public void setUp() {
     sectors.add(new Sector(1, "Manufacturing",1, ""));
     sectors.add(new Sector(2,"Construction materials",19, "Manufacturing"));
     sectors.add(new Sector(3, "Food and beverage",6, "Manufacturing"));
     }

     @Test
     public void createSectorDataList() {
        List<SectorData> sortedSectors = sectorService.sortSectors(sectors);
        SectorData firstChild = sectorService.createSectorData(sectors.get(1));
        SectorData secondChild = sectorService.createSectorData(sectors.get(2));
        assertEquals(sortedSectors.size(), 1);
        assertEquals(sortedSectors.get(0).getChildren().get(0).getId(), firstChild.getId());
        assertEquals(sortedSectors.get(0).getChildren().get(1).getId(), secondChild.getId());
     }
}