import java.util.ArrayList;
import models.*;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertArrayEquals;

/**
 * Write a description of class AutoHarness here.
 * 
 * @author Luveshen Pillay, Christopher Mudongo, Rayaan Fakier	
 * @version 2017/09/25
 */
public class AutoTest {

    @Test
    public void Course(){
        SLSystem CONTROLLER = new SLSystem();
        ArrayList<Unit> UNITS =  CONTROLLER.getUnits();
        
        assertTrue(UNITS.size() > 0);
        
        Unit U = UNITS.get(0);
        
        // Unit getters
        assertEquals(U.getID(), "02");
        assertEquals(U.getTitle(), "Word Processing ");
        assertEquals(U.toString(), uString);

        Unit otherU = UNITS.get(1);
        assertEquals(otherU.getID(), "01");
        assertEquals(otherU.getTitle(), "Word processing");
        
        // Unit navigation
        assertTrue(otherU.next() == null);
        assertTrue(otherU.previous() == null);
        
        assert(U.hasNext());
        assertFalse(U.hasPrevious());
        Lesson L;
        L = U.next();
        assertTrue(U != null);
        
        // Lesson getters
        assertEquals(L.getCategory(), "O");
        assertEquals(L.getID(), "01");
        assertEquals(L.getTitle(), "O1: Getting to know your computer");
        
        // Lesson navigation
        assertFalse(L.hasNext());
        assertFalse(L.hasPrevious());
        
        // increment to only Lesson with Activities
        for (int i = 0; i < 4; ++i){
            U.next();
        }
        L = U.next();        
        LessonActivity LA = L.next();
        
        // LessonActivity getters
        String[] atts = {"lesson_description_2", "/video/What is an organization.mp4", "What is an organization", null};
        assertArrayEquals(LA.getAttributes(), atts);
        
        String attribute = LA.getScreenID();
        assertEquals(attribute, atts[0]);
        attribute = LA.getVideoUrl();
        assertEquals(attribute, atts[1]);
        attribute = LA.getVideoCaption();
        assertEquals(attribute, atts[2]);
        attribute = LA.getImagePath();
        assertEquals(attribute, atts[3]);
    }
    
    private final String uString = "Unit: 02)Word Processing \n" +
"    Lesson: 01) O1: Getting to know your computer [O]\n" +
"    Lesson: 02) O2: Hardware hunt [O]\n" +
"    Lesson: 03) O3: Our Computerized world [O]\n" +
"    Lesson: 05) O4: Special Keys [O]\n" +
"    Lesson: 05) E6: Files and Media [E]\n" +
"    Lesson: 06) S3:Our Organization [S]\n" +
"      lesson_description_1) /video/Introduction.mp4 Introduction [no image]\n" +
"      lesson_description_2) /video/What is an organization.mp4 What is an organization [no image]\n" +
"      lesson_description_3) /video/How are organizations the same.mp4 How are organizations the same [no image]\n" +
"      lesson_description_4) /video/Roles and responsibility.mp4 Roles and responsibility [no image]\n" +
"      lesson_description_5) /video/Organization structure.mp4 Organization structure [no image]\n" +
"      lesson_description_6) /video/DCCT structure.mp4 DCCT structure [no image]\n" +
"      lesson_description_7) /video/DCCT hierarchy.mp4 DCCT hierarchy [no image]\n" +
"      task_description2) /video/Task description.mp4 Task description [no image]\n" +
"      step_1) /video/Task 1 - look for e-learner icon.mp4 Task 1 - look for e-learner icon /shared_images/e-learner.png\n" +
"      step_2) /video/Task 2 - Categories (O, E , S).mp4 Task 2 - Categories (O, E , S) [no image]\n" +
"      step_3) /video/Task 3 - choose S3 our company.mp4 Task 3 - choose S3 our company [no image]\n" +
"      step_4) /video/Task 4 - Password.mp4 Task 4 - Password [no image]\n" +
"      step_5) /video/Task 5 - Open Word.mp4 Task 5 - Open Word /shared_images/word_icon.png\n" +
"      step_6) /video/Task 6 - How to open MS Word.mp4 Task 6 - How to open MS Word [no image]\n" +
"      step_7) /video/Task 7 - New document.mp4 Task 7 - New document [no image]\n" +
"      step_8) /video/Task 8 - Save document.mp4 Task 8 - Save document [no image]\n" +
"      step_9) /video/Task 9 - Insert heading.mp4 Task 9 - Insert heading [no image]\n" +
"      step_10) /video/Task 10 - Look for WordArt.mp4 Task 10 - Look for WordArt /shared_images/WordArt_icon.png\n" +
"      step_11) /video/Task 11 - choose wordArt.mp4 Task 11 - choose wordArt [no image]\n" +
"      step_12) /video/Task 12 - Type your heading.mp4 Task 12 - Type your heading [no image]\n" +
"      step_13) /video/Task 13 - Type my organization.mp4 Task 13 - Type my organization [no image]\n" +
"      step_14) /video/Task 14 - change wordArt design.mp4 Task 14 - change wordArt design [no image]\n" +
"      step_15) /video/Task 15 - Press Ok to confirm selection.mp4 Task 15 - Press Ok to confirm selection [no image]\n" +
"      step_16) /video/Task 16 - Adding 3D.mp4 Task 16 - Adding 3D [no image]\n" +
"      step_17) /video/Task 17 - Locating 3D on toolbar.mp4 Task 17 - Locating 3D on toolbar [no image]\n" +
"      step_18) /video/Task 18 - Choose 3D.mp4 Task 18 - Choose 3D [no image]\n" +
"      step_19) /video/Task 19 - Text wrapping.mp4 Task 19 - Text wrapping [no image]\n" +
"      step_20) /video/Task 20 - Locate smartArt.mp4 Task 20 - Locate smartArt /shared_images/Insert_smartArt.png\n" +
"      step_21) /video/Task 21 - move cursor to position.mp4 Task 21 - move cursor to position [no image]\n" +
"      step_22) /video/Task 22 - Insert smartArt.mp4 Task 22 - Insert smartArt /shared_images/smartArt_hierarchy.png\n" +
"      step_23) /video/Task 23 - choose organization chart.mp4 Task 23 - choose organization chart [no image]\n" +
"      step_24) /video/Task 24 - smartArt text wrapping.mp4 Task 24 - smartArt text wrapping /shared_images/smartArt_ToolBox.png\n" +
"      step_25) /video/Task 25 - Move smartArt.mp4 Task 25 - Move smartArt [no image]\n" +
"      step_26) /video/Task 26 - Fill in organization chart.mp4 Task 26 - Fill in organization chart [no image]\n" +
"      step_27) /video/Task 27 - Add new box.mp4 Task 27 - Add new box /shared_images/addShape_dropdown.png\n" +
"      step_28) /video/Task 28 - Adding more boxes.mp4 Task 28 - Adding more boxes [no image]\n" +
"      step_29) /video/Task 29 - Add all jobs to chart.mp4 Task 29 - Add all jobs to chart [no image]\n" +
"      step_30) /video/Task 30 - How to move boxes.mp4 Task 30 - How to move boxes [no image]\n" +
"      step_31) /video/Task 31 - Moving boxes.mp4 Task 31 - Moving boxes [no image]\n" +
"      step_32) /video/Task 32 - Matching DCCT structure.mp4 Task 32 - Matching DCCT structure [no image]\n" +
"      step_33) /video/Task 33 -  Change colour.mp4 Task 33 -  Change colour /shared_images/smartArt_designToolbar.png\n" +
"      step_34) /video/Task 34 - Type name and date.mp4 Task 34 - Type name and date [no image]\n" +
"      step_35) /video/Task 35 - Save document.mp4 Task 35 - Save document /shared_images/quickAccessRibbon_save.png\n" +
"      step_36) /video/Task 36 - Print preview.mp4 Task 36 - Print preview [no image]\n" +
"      step_37) /video/Task 37 - Opening print preview.mp4 Task 37 - Opening print preview /shared_images/print_preview.png\n" +
"      step_38) /video/Task 38 - Close print preview.mp4 Task 38 - Close print preview /shared_images/close_printPreview.png\n" +
"      step_39) /video/Task 39 - Opening print.mp4 Task 39 - Opening print [no image]\n" +
"      step_40) /video/Task 40 - Print dialog box.mp4 Task 40 - Print dialog box [no image]\n" +
"      step_41) /video/Task 41 - Save check.mp4 Task 41 - Save check [no image]\n" +
"      step_42) /video/Task 42 - Close MS Word.mp4 Task 42 - Close MS Word /shared_images/close_button_red.png\n" +
"      step_43) /video/Task 43 - End of lesson.mp4 Task 43 - End of lesson [no image]\n" +
"    Lesson: 04) S4: Tax - What When and How [S]\n" +
"    Lesson: 04) S5: Calculating Costs [S]";
}
