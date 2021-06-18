package tests;

import data.Student;
import data.StudentRegistrationFormData;
import extensions.ScreenshotOnFailure;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.StudentRegistrationFormPage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;

@ExtendWith({ScreenshotOnFailure.class})
@DisplayName("Student registration form tests")
public class StudentRegistrationFormTests extends TestBase {
    private Student student;
    private Map<String, String> expectedData;
    private static final String URL = "https://demoqa.com/automation-practice-form";

    @BeforeEach
    void generateData() {
        student = StudentRegistrationFormData.generateStudent();
        expectedData = StudentRegistrationFormData.generateExpectedData(student);
    }

    @Test
    @Owner("omelyashchik")
    @Feature("Registration form")
    @DisplayName("Successful fill student registration form")
    void successfulFillStudentRegistrationForm() {
        StudentRegistrationFormPage studentRegistrationFormPage =
                open(URL, StudentRegistrationFormPage.class);

        studentRegistrationFormPage.setStudentData(student)
                .submit()
                .popupShouldAppear();

        SoftAssertions softly = new SoftAssertions();
        for (Map.Entry<String, String> data : expectedData.entrySet()) {
            String rowName = data.getKey();
            String expectedValueInTableRow = data.getValue();

            Allure.step("Check that row '" + rowName + "' contains value '" + expectedValueInTableRow + "'", () -> {
                String actualValueInTableRow = studentRegistrationFormPage.getValueInTableRow(rowName);
                softly.assertThat(actualValueInTableRow).as(rowName).isEqualTo(expectedValueInTableRow);
            });
        }
        softly.assertAll();
    }
    @Test
    @Owner("omelyashchik")
    @Feature("Registration form")
    @DisplayName("Unsuccessful fill student registration form")
    void unsuccessfulFillStudentRegistrationForm() {
        StudentRegistrationFormPage studentRegistrationFormPage =
                open(URL, StudentRegistrationFormPage.class);

        studentRegistrationFormPage.setStudentData(student)
                .popupShouldAppear();
    }
}
