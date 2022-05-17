package de.volkswagen.f73.controller;

import de.volkswagen.f73.view.*;

/**
 * The class "SideBarViewController"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class SideBarViewController {

    private final SideBarView sideBarView;

    public SideBarViewController(SideBarView sideBarView) {
        this.sideBarView = sideBarView;
        initContent();
    }

    private void initContent() {
        initOverviewButton();
        initAnimalButton();
        initEnclosureButton();
        initStallButton();
        initEmployeeButton();
        initFinaceButton();
        initReportButton();
    }

    private void initOverviewButton() {
        this.sideBarView.getOverviewButton().setOnAction((e) -> {
            if(!(this.sideBarView.getScene().getRoot() instanceof OverviewView)){
                OverviewView overviewView = new OverviewView();
                OverviewViewController overviewController = new OverviewViewController(overviewView);
                this.sideBarView.getScene().setRoot(overviewView);
            }
        });
    }

    private void initAnimalButton() {
        this.sideBarView.getAnimalButton().setOnAction((e) -> {
            if(!(this.sideBarView.getScene().getRoot() instanceof AnimalView)){
                AnimalView animalView = new AnimalView();
                AnimalViewController animalController = new AnimalViewController(animalView);
                this.sideBarView.getScene().setRoot(animalView);
            }
        });
    }

    private void initEnclosureButton() {
        this.sideBarView.getEnclosureButton().setOnAction((e) -> {
            if(!(this.sideBarView.getScene().getRoot() instanceof EnclosureView)){
                EnclosureView enclosureView = new EnclosureView();
                EnclosureViewController enclosureViewController = new EnclosureViewController(enclosureView);
                this.sideBarView.getScene().setRoot(enclosureView);
            }
        });
    }

    private void initStallButton() {
        this.sideBarView.getStallButton().setOnAction((e) -> {
            if(!(this.sideBarView.getScene().getRoot() instanceof StallView)){
                StallView stallView = new StallView();
                StallViewController stallViewController = new StallViewController(stallView);
                this.sideBarView.getScene().setRoot(stallView);
            }
        });
    }

    private void initEmployeeButton() {
        this.sideBarView.getEmployeeButton().setOnAction((e) -> {
           if(!(this.sideBarView.getScene().getRoot() instanceof EmployeeView)) {
               EmployeeView employeeView = new EmployeeView();
               EmployeeViewController employeeViewController = new EmployeeViewController(employeeView);
               this.sideBarView.getScene().setRoot(employeeView);
           }
        });
    }

    private void initFinaceButton() {
        this.sideBarView.getFinanceButton().setOnAction((e) -> {
            if(!(this.sideBarView.getScene().getRoot() instanceof FinanceView)){
                FinanceView finaceView = new FinanceView();
                FinanceViewController finaceViewController = new FinanceViewController(finaceView);
                this.sideBarView.getScene().setRoot(finaceView);
            }
        });
    }

    private void initReportButton() {
        this.sideBarView.getReportButton().setOnAction((e) -> {
            if(!(this.sideBarView.getScene().getRoot() instanceof ReportView)){
                ReportView reportView = new ReportView();
                ReportViewController reportViewController = new ReportViewController(reportView);
                this.sideBarView.getScene().setRoot(reportView);
            }
        });
    }
}
