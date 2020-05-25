package Application.Services;

import Application.Entities.Edition;

import java.util.Date;
import java.util.List;

public interface EditionService {
    Edition save(Edition edition);
    void delete(Long id);
    Edition update(Edition edition);
    Edition findById(Long id);
    List<Edition> findAll();
    List<Edition> findByIdLib(Long IdLib);
    List<Edition> findByHallNum(int hallNum);
    List<Edition> findByRackNum(int rackNum);
    List<Edition> findByShelfNum(int shelfNum);

    List<Edition> findByMoreDateAdding(Date dateAdding);
    List<Edition> findByLessDateAdding(Date dateAdding);
    List<Edition> findByMoreDateRemoving(Date dateRemoving);
    List<Edition> findByLessDateRemoving(Date dateRemoving);

    List<Edition> findByDateAddingAndDateRemoving(Date dateAdding, Date dateRemoving);

    List<Edition> findByIdLibAndHallNum(Long IdLib,int hallNum);
    List<Edition> findByIdLibAndRackNum(Long IdLib,int rackNum);
    List<Edition> findByIdLibAndShelfNum(Long IdLib,int shelfNum);

    List<Edition> findByHallNumAndRackNum(int hallNum,int rackNum);
    List<Edition> findByHallNumAndShelfNum(int hallNum,int shelfNum);

    List<Edition> findByRackNumAndShelfNum(int rackNum,int shelfNum);

    List<Edition> findByIdLibAndHallNumAndRackNum(Long IdLib,int hallNum,int rackNum);
    List<Edition> findByIdLibAndHallNumAndShelfNum(Long IdLib,int hallNum,int shelfNum);
    List<Edition> findByIdLibAndRackNumAndShelfNum(Long IdLib,int rackNum,int shelfNum);
    List<Edition> findByHallNumAndRackNumAndShelfNum(int hallNum,int rackNum,int shelfNum);

    List<Edition> findByAll(Long IdLib,int hallNum, int rackNum,int shelfNum);
}
