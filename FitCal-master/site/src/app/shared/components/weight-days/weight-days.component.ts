import { Component, Input, OnInit } from '@angular/core';
import { WeightDay } from '@shared/interfaces/weightDayInterface';
import { WeightDayService } from '@shared/services/weightDay.service';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from '../confirmation-dialog/confirmation-dialog.component';
import { DialogCreateWeightDayComponent } from '../dialog-create-weight-day/dialog-create-weight-day.component';

@Component({
  selector: 'app-weight-days',
  templateUrl: './weight-days.component.html',
  styleUrls: ['./weight-days.component.scss']
})
export class WeightDaysComponent implements OnInit {
  weightDays: WeightDay[] = [];
  @Input() user: any; // For recalculation

  view: [number, number] = [400, 300];
  colorScheme: any = {
    domain: ['#F19FB3', '#F9C2CF', '#F7E5B8', '#CED4DB'] // Bar colors
  };
  chartData: any[] = [];


  constructor(
    private matDialog: MatDialog,
    private weightDayService: WeightDayService
  ) {}

  ngOnInit() {
    // Call a method to get weight data when the component initializes
    this.getWeightDays();
  }

  getWeightDays() {
    // Call WeightDayService to get weight data for the current user
    this.weightDayService.getWeightDaysByUserId(this.user.id).subscribe((data: WeightDay[]) => {
      this.weightDays = data;

      this.weightDays.sort((a, b) => {
        const dateA = new Date(a.date);
        const dateB = new Date(b.date);
        return dateA.getTime() - dateB.getTime();
      });

      console.log(this.weightDays);

      // Call a method to update chart data with the obtained weight data
      this.updateChartData();
    });
  }

  updateChartData() {
    // Clear existing chart data
    this.chartData = [];

    // Loop through the weightDays array and populate the chartData array
    for (const weightDay of this.weightDays) {
      const chartEntry = {
        name: weightDay.date, // Date as x-axis value
        value: weightDay.weight // Weight as y-axis value
      };
      this.chartData.push(chartEntry);
    }
  }

  openNewWeightDayComponent() {
    const createWeightDaysComponent = this.matDialog.open(DialogCreateWeightDayComponent);
    createWeightDaysComponent.afterClosed().subscribe((weightDay) => {
      this.weightDayService.createWeightDay(weightDay).subscribe(()=> {
        window.location.reload();
      }, (error) =>{
        console.error('Error while adding weight:', error);
      });
    });
  }

  editWeightDay(weightDay: WeightDay) {
    const dialogRef = this.matDialog.open(DialogCreateWeightDayComponent, {
      width: '400px',
      data: weightDay, // Pass the weightDay to edit as data for the dialog
    });

    dialogRef.afterClosed().subscribe((result: WeightDay) => {
      this.weightDayService.updateWeightDay(weightDay).subscribe(()=> {
        window.location.reload();
      }, (error) =>{
        console.error('Error while updating weight:', error);
      });
    });
  }

  deleteWeightDay(weightDay: WeightDay) {
    const dialogRef = this.matDialog.open(ConfirmationDialogComponent, {
      width: '400px',
      data: 'Are you sure you want to delete this entry?',
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.weightDayService.deleteWeightDay(weightDay.id).subscribe((result) => {
          // Deleted successfully
          window.location.reload();
        }, (error) => {
          console.log("Error while deleting. WeightDay ID: ", weightDay.id!, error);
        });
      }
    });
  }
}
