import { Component } from '@angular/core';

@Component({
  imports: [],
  selector: 'app-register',
  styleUrl: './register.scss',
  templateUrl: './register.html',
})
export class Register {

  strength = 0;
  strengthMessage = 'Nên có chứ hoa, chữ sô và ký tự đặc biệt';
  barColor = "var(--primary-light)";

  checkPassword(password: string): void {

    let score = 0;


    if (password.length >= 8) score++
    if (/[0-9]/.test(password)) score++
    if (/[a-zA-Z]/.test(password)) score++
    if (/[^a-zA-Z0-9]/.test(password)) score++

    if (password.length == 0) {
      this.strength == 0;
      this.strengthMessage = 'Nên có chứ hoa, chữ sô và ký tự đặc biệt';
      this.barColor = "var(--primary-light)";
    }
    else if (score <= 1) {
      this.strength = 1;
      this.strengthMessage = 'Độ mạnh: Yếu';
      this.barColor = "var(--danger)";
    }
    else if (score <= 2) {
        this.strength = 2;
        this.strengthMessage = 'Độ mạnh: Khá mạnh';
        this.barColor = "var(--warning)";
    }
    else if (score <= 3) {
        this.strength = 3;
        this.strengthMessage = 'Độ mạnh: Mạnh';
        this.barColor = "var(--success)";
    }
    else {
        this.strength = 4;
        this.strengthMessage = 'Độ mạnh: Rất mạnh';
        this.barColor = "var(--success)";
    }
  }
}
