import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PageHead } from './page-head';

describe('PageHead', () => {
  let component: PageHead;
  let fixture: ComponentFixture<PageHead>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PageHead],
    }).compileComponents();

    fixture = TestBed.createComponent(PageHead);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
