import { Component } from '@angular/core';
import { ActivatedRoute, RouterOutlet } from '@angular/router';

@Component({
  imports: [RouterOutlet],
  selector: 'app-layout',
  styleUrl: './layout.scss',
  templateUrl: './layout.html',
})
export class Layout {

  constructor(
    private route: ActivatedRoute
  ){}

  
  bannerConfig = {
    login: {
      quote: "Một thư viện tốt là nơi mọi câu hỏi đều có thể tìm được khởi đầu của câu trả lời."
    },
    register: {
      quote: 'Tham gia cùng hơn 3.900 thành viên — mượn sách trực tuyến chỉ trong vài phút.'
    }
  };

  currentBanner = this.bannerConfig.login;

  ngOnInit() {
    this.route.firstChild?.data.subscribe(data => {
      const pageType = data['pageType'];

      this.currentBanner = this.bannerConfig[pageType as keyof typeof this.bannerConfig];

      console.log(this.currentBanner.quote)
    })
  }
}
