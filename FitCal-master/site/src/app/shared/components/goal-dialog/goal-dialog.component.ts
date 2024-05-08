import { Component, Input } from '@angular/core';
import { DisableRecalculator } from '@shared/services/disableRecalculator.service';

@Component({
  selector: 'app-goal-dialog',
  templateUrl: './goal-dialog.component.html',
  styleUrls: ['./goal-dialog.component.scss'],
})
export class GoalDialogComponent {
  @Input() selectTipo: string = '';

  @Input() selectedOption: string = '';

  constructor(
    public disableRecalculator: DisableRecalculator,
  ) {}

  weeklyGoals: any[] = [
    { value: 'GAIN1000', label: 'Gain 1kg per week' },
    { value: 'GAIN750', label: 'Gain 0.75kg per week' },
    { value: 'GAIN500', label: 'Gain 0.5kg per week' },
    { value: 'GAIN250', label: 'Gain 0.25kg per week' },
    { value: 'MAINTENANCE', label: 'Maintenance' },
    { value: 'LOSE250', label: 'Lose 0.25kg per week' },
    { value: 'LOSE500', label: 'Lose 0.5kg per week' },
    { value: 'LOSE750', label: 'Lose 0.75kg per week' },
    { value: 'LOSE1000', label: 'Lose 1kg per week' },
  ];

  activityLevels: any[] = [
    { value: 'ANY', label: 'Sedentary' },
  ]
}
