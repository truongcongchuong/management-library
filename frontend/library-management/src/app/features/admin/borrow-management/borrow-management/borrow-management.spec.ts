import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BorrowManagement } from './borrow-management';

describe('BorrowManagement', () => {
  let component: BorrowManagement;
  let fixture: ComponentFixture<BorrowManagement>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BorrowManagement],
    }).compileComponents();

    fixture = TestBed.createComponent(BorrowManagement);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
