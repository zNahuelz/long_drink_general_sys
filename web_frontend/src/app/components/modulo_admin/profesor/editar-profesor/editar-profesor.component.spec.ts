import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarProfesorComponent } from './editar-profesor.component';

describe('EditarProfesorComponent', () => {
  let component: EditarProfesorComponent;
  let fixture: ComponentFixture<EditarProfesorComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditarProfesorComponent]
    });
    fixture = TestBed.createComponent(EditarProfesorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
