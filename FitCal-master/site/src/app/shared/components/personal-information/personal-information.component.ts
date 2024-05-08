import { Component, EventEmitter, Input, Output } from '@angular/core';
import { DisableRecalculator } from '@shared/services/disableRecalculator.service';

@Component({
  selector: 'app-personal-information',
  templateUrl: './personal-information.component.html',
  styleUrls: ['./personal-information.component.scss']
})
export class PersonalInformationComponent {
  @Input() data: string = '';
  @Input() mainData: string = '';
  @Input() type: string = '';

  newData: number = 0;
  editData = false;
  previousValue: string = '';

  @Output() mainDataChange = new EventEmitter<string>();

  constructor(private disableRecalculator: DisableRecalculator) {}

  onInputChange(event: any) {
    const value = event.target.value;
    if ((this.data === 'Height:' || this.data === 'Weight:') && parseInt(value) < 0) {
      console.error(this.data === 'Height:' ? 'Height cannot be negative' : 'Weight cannot be negative');
      this.newData = parseInt(this.mainData); // Restore the previous value
    } else {
      this.newData = parseInt(value);
    }
  }

  saveData() {
    if ((this.data === 'Height:' || this.data === 'Weight:') && this.newData < 0) {
      console.error(this.data === 'Height:' ? 'Height cannot be negative' : 'Weight cannot be negative');
      this.newData = parseInt(this.mainData); // Restore the previous value
      return;
    }

    this.mainData = '' + this.newData;
    this.disableRecalculator.disableRecalculate();
    this.editData = false;
    this.mainDataChange.emit(this.mainData);
  }

  cancelData() {
    this.editData = false;
    this.newData = parseInt(this.mainData); // Restore the previous value
  }
}
