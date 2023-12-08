import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { IEvaluation } from '../../../models/evaluation.models';

@Component({
  selector: 'app-eval-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './eval-list.component.html',
  styleUrl: './eval-list.component.css'
})
export class EvalListComponent {

  @Input() public evaluations ?: IEvaluation[];

}
