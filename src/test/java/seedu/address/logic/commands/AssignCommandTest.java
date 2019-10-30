package seedu.address.logic.commands;

import static seedu.address.logic.commands.AssignCommand.MESSAGE_ASSIGN_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.SampleEntity.VALID_DRIVER;
import static seedu.address.testutil.SampleEntity.VALID_TASK_ID;
import static seedu.address.testutil.SampleEntity.getSampleFreshModel;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.GlobalClock;
import seedu.address.model.EventTime;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.SampleEntity;

class AssignCommandTest {
    Model model;

    /**
     * Sets the clock to 15 Oct 2019, 2pm.
     */
    @BeforeAll
    static void setStaticClock() {
        GlobalClock.setFixedClock();
    }

    @AfterAll
    static void setNormalClock() {
        GlobalClock.setRealClock();
    }

    @BeforeEach
    void setFreshModel() {
        this.model = getSampleFreshModel();
    }

    @Test
    void execute_addTaskNow_shouldSucceed() {
        EventTime proposed = EventTime.parse("1400", "1500");
        AssignCommand cmd = new AssignCommand(VALID_DRIVER.getId(), VALID_TASK_ID, proposed, false);

        Model expectedModel = getSampleFreshModel();
        expectedModel.getDriver(VALID_DRIVER.getId()).addToSchedule(proposed);
        expectedModel.getTask(VALID_TASK_ID).setDriver();

        assertCommandSuccess(cmd, model, new CommandResult(String.format(MESSAGE_ASSIGN_SUCCESS, VALID_DRIVER.getName().fullName,
                proposed);


    }
}