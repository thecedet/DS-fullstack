import { Component, EventEmitter, Input, Output } from '@angular/core';
import { IEvaluationCreate } from '../../../models/evaluation.models';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-eval-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './eval-form.component.html',
  styleUrl: './eval-form.component.css'
})
export class EvalFormComponent {

  @Input() public evaluation ?: IEvaluationCreate;
  @Output() public create : EventEmitter<IEvaluationCreate> = new EventEmitter();



  public submit(form : NgForm) : void {
    if(form.valid) {
      this.create.emit(this.evaluation)
      form.reset(this.evaluation)
    }
  }

}
