import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AppChart } from './app-chart';

describe('AppChart', () => {
  let component: AppChart;
  let fixture: ComponentFixture<AppChart>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppChart],
    }).compileComponents();

    fixture = TestBed.createComponent(AppChart);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
