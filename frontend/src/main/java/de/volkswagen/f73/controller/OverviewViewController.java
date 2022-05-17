package de.volkswagen.f73.controller;

import de.volkswagen.f73.util.ExportUtil;
import de.volkswagen.f73.view.OverviewView;
import io.swagger.client.model.ZooAccount;
import org.threeten.bp.LocalDate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The class "OverviewViewController"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class OverviewViewController {

    private final OverviewView overviewView;

    public OverviewViewController(OverviewView overviewView) {
        this.overviewView = overviewView;
        configureContent();
    }

    private void configureContent() {
        this.overviewView.getTestButton().setOnAction((e) -> {
           String path = "src/main/resources/de/volkswagen/f73/frontend/test.xlsx";
           List<ZooAccount> tmp = new ArrayList<>();
           for(int i=0; i < 5; i++){
               double value = (Math.random() + 1.0) * 100;
               BigDecimal valueOfBooking = new BigDecimal(value);
               ZooAccount account = new ZooAccount()
                       .date(LocalDate.now())
                       .usageOfBooking("Hier könnte ihre Werbung stehen" + i)
                       .valueOfBooking(valueOfBooking)
                       .bankBalance(valueOfBooking.pow(2));
               tmp.add(account);
           }
           ExportUtil.exportIncomesOrExpansesToExcel(path, tmp, "Ausgaben");
        });
    }
}