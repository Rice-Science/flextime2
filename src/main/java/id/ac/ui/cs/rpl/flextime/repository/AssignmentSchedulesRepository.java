package id.ac.ui.cs.rpl.flextime.repository;
import id.ac.ui.cs.rpl.flextime.model.AssignmentSchedules;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class AssignmentSchedulesRepository {
    private final List<AssignmentSchedules> assignmentData = new ArrayList<>();
    public AssignmentSchedules create(AssignmentSchedules assignment){
        if(assignment.getAssignmentSchedulesId() == null){
            UUID uuid = UUID.randomUUID();
            assignment.setAssignmentSchedulesId(uuid.toString());
        }
        assignmentData.add(assignment);
        return assignment;
    }
    public Iterator<AssignmentSchedules> findAll(){
        return assignmentData.iterator();
    }

    public AssignmentSchedules findById(String id){
        for (AssignmentSchedules assignment: assignmentData){
            if (assignment.getAssignmentSchedulesId().equals(id)){
                return assignment;
            }
        }
        return null;
    }
    public AssignmentSchedules update(AssignmentSchedules updatedAssignment){
        for (int i = 0; i < assignmentData.size(); i++) {
            if (assignmentData.get(i).getAssignmentSchedulesId().equals(updatedAssignment.getAssignmentSchedulesId())) {
                assignmentData.set(i, updatedAssignment);
                return updatedAssignment;
            }
        }
        return null;
    }
    public void delete(String id){ assignmentData.removeIf(assignment -> assignment.getAssignmentSchedulesId().equals(id));
    }
}