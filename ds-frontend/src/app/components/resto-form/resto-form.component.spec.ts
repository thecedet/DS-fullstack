import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RestoFormComponent } from './resto-form.component';

describe('RestoFormComponent', () => {
  let component: RestoFormComponent;
  let fixture: ComponentFixture<RestoFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RestoFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RestoFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
