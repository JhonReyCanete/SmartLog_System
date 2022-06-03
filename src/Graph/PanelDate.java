package Graph;

import java.awt.Component;
import java.util.Calendar;
import java.util.Date;

public class PanelDate extends javax.swing.JLayeredPane {

    private int month;
    private int year;

    public PanelDate(int month, int year) {
        initComponents();
        this.month = month;
        this.year = year;
        init();
    }

    private void init() {
        
        mon.asTitle();
        tue.asTitle();
        wed.asTitle();
        thu.asTitle();
        fri.asTitle();
        sat.asTitle();
        Sun.asTitle();
        setDate();
    }

    private void setDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);  //  month jan as 0 so start from 0
        calendar.set(Calendar.DATE, 1);
        int startDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;  //  get day of week -1 to index
        calendar.add(Calendar.DATE, -startDay);
        ToDay toDay = getToDay();
        for (Component com : getComponents()) {
            Cell cell = (Cell) com;
            if (!cell.isTitle()) {
                cell.setText(calendar.get(Calendar.DATE) + "");
                cell.setDate(calendar.getTime());
                cell.currentMonth(calendar.get(Calendar.MONTH) == month - 1);
                if (toDay.isToDay(new ToDay(calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR)))) {
                    cell.setAsToDay();
                }
                calendar.add(Calendar.DATE, 1); //  up 1 day
            }
        }
    }

    private ToDay getToDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return new ToDay(calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sun = new Graph.Cell();
        Sun = new Graph.Cell();
        mon = new Graph.Cell();
        tue = new Graph.Cell();
        wed = new Graph.Cell();
        thu = new Graph.Cell();
        fri = new Graph.Cell();
        sat = new Graph.Cell();
        cell10 = new Graph.Cell();
        cell9 = new Graph.Cell();
        cell12 = new Graph.Cell();
        cell11 = new Graph.Cell();
        cell13 = new Graph.Cell();
        cell14 = new Graph.Cell();
        cell15 = new Graph.Cell();
        cell16 = new Graph.Cell();
        cell17 = new Graph.Cell();
        cell18 = new Graph.Cell();
        cell19 = new Graph.Cell();
        cell20 = new Graph.Cell();
        cell21 = new Graph.Cell();
        cell22 = new Graph.Cell();
        cell23 = new Graph.Cell();
        cell24 = new Graph.Cell();
        cell25 = new Graph.Cell();
        cell26 = new Graph.Cell();
        cell27 = new Graph.Cell();
        cell28 = new Graph.Cell();
        cell29 = new Graph.Cell();
        cell30 = new Graph.Cell();
        cell31 = new Graph.Cell();
        cell32 = new Graph.Cell();
        cell33 = new Graph.Cell();
        cell34 = new Graph.Cell();
        cell35 = new Graph.Cell();
        cell36 = new Graph.Cell();
        cell37 = new Graph.Cell();
        cell38 = new Graph.Cell();
        cell39 = new Graph.Cell();
        cell40 = new Graph.Cell();
        cell41 = new Graph.Cell();
        cell42 = new Graph.Cell();
        cell43 = new Graph.Cell();
        cell44 = new Graph.Cell();
        cell45 = new Graph.Cell();
        cell46 = new Graph.Cell();
        cell47 = new Graph.Cell();
        cell48 = new Graph.Cell();
        cell49 = new Graph.Cell();
        cell50 = new Graph.Cell();

        sun.setText("Sun");

        setLayout(new java.awt.GridLayout(7, 7));

        Sun.setForeground(new java.awt.Color(254, 90, 57));
        Sun.setText("Sun");
        Sun.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(Sun);

        mon.setText("Mon");
        mon.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(mon);

        tue.setText("Tue");
        tue.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(tue);

        wed.setText("Wed");
        wed.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(wed);

        thu.setText("Thu");
        thu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(thu);

        fri.setText("Fri");
        fri.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(fri);

        sat.setText("Sat");
        sat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(sat);

        cell10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell10);

        cell9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell9);

        cell12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell12);

        cell11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell11);

        cell13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell13);

        cell14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell14);

        cell15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell15);

        cell16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell16);

        cell17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell17);

        cell18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell18);

        cell19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell19);

        cell20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell20);

        cell21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell21);

        cell22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell22);

        cell23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell23);

        cell24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell24);

        cell25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell25);

        cell26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell26);

        cell27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell27);

        cell28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell28);

        cell29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell29);

        cell30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell30);

        cell31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell31);

        cell32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell32);

        cell33.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell33);

        cell34.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell34);

        cell35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell35);

        cell36.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell36);

        cell37.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell37);

        cell38.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell38);

        cell39.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell39);

        cell40.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell40);

        cell41.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell41);

        cell42.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell42);

        cell43.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell43);

        cell44.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell44);

        cell45.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell45);

        cell46.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell46);

        cell47.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell47);

        cell48.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell48);

        cell49.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell49);

        cell50.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(cell50);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Graph.Cell Sun;
    private Graph.Cell cell10;
    private Graph.Cell cell11;
    private Graph.Cell cell12;
    private Graph.Cell cell13;
    private Graph.Cell cell14;
    private Graph.Cell cell15;
    private Graph.Cell cell16;
    private Graph.Cell cell17;
    private Graph.Cell cell18;
    private Graph.Cell cell19;
    private Graph.Cell cell20;
    private Graph.Cell cell21;
    private Graph.Cell cell22;
    private Graph.Cell cell23;
    private Graph.Cell cell24;
    private Graph.Cell cell25;
    private Graph.Cell cell26;
    private Graph.Cell cell27;
    private Graph.Cell cell28;
    private Graph.Cell cell29;
    private Graph.Cell cell30;
    private Graph.Cell cell31;
    private Graph.Cell cell32;
    private Graph.Cell cell33;
    private Graph.Cell cell34;
    private Graph.Cell cell35;
    private Graph.Cell cell36;
    private Graph.Cell cell37;
    private Graph.Cell cell38;
    private Graph.Cell cell39;
    private Graph.Cell cell40;
    private Graph.Cell cell41;
    private Graph.Cell cell42;
    private Graph.Cell cell43;
    private Graph.Cell cell44;
    private Graph.Cell cell45;
    private Graph.Cell cell46;
    private Graph.Cell cell47;
    private Graph.Cell cell48;
    private Graph.Cell cell49;
    private Graph.Cell cell50;
    private Graph.Cell cell9;
    private Graph.Cell fri;
    private Graph.Cell mon;
    private Graph.Cell sat;
    private Graph.Cell sun;
    private Graph.Cell thu;
    private Graph.Cell tue;
    private Graph.Cell wed;
    // End of variables declaration//GEN-END:variables
}
