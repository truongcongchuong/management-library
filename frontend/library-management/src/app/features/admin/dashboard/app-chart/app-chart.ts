import { Component,AfterViewInit } from '@angular/core';
import Chart from 'chart.js/auto';

@Component({
  imports: [],
  selector: 'app-app-chart',
  styleUrl: './app-chart.scss',
  templateUrl: './app-chart.html',
})
export class AppChart implements AfterViewInit {

  ngAfterViewInit(): void {

    const canvas = document.getElementById('bars') as HTMLCanvasElement;

    const ctx = canvas.getContext('2d');

    if (!ctx) {return;}

    const styles = getComputedStyle(document.documentElement);

    const primary = styles.getPropertyValue('--primary').trim();

    const gradient = ctx.createLinearGradient(0,0,300,150);

    gradient.addColorStop(0,primary);

    gradient.addColorStop(1,'#1B3BC2');

    new Chart(ctx, {

      type: 'bar',

      data: {
        labels: ['Jan','Feb','Mar','Apr','May'],
        datasets: [
          {
            label: 'Borrow Records',
            data: [12,19,3,5,2],
            backgroundColor: gradient,
            borderRadius: 5,
            borderSkipped: false
          }
        ]
      },
       options: {
        plugins: {

          legend: {
            display: false
          }

        },
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          x: {
            grid: {
              display: false
            }
          },
          y: {
            display: false,
            grid: {
              display: false
            }
          }
        }
      }

      // 
    });

    const canvas_2 = document.getElementById('book-in-library') as HTMLCanvasElement;

    const centerTextPlugin = {
      id: 'centerText',

      afterDraw(chart: any) {

        const {
          ctx,
          chartArea: {
            width,
            height
          }
        } = chart;

        ctx.save();

        ctx.font = 'bold 28px Inter';

        ctx.fillStyle = '#10192B';

        ctx.textAlign = 'center';

        ctx.fillText(
          '68%',
          width / 2,
          height / 2
        );

        ctx.font = '14px Inter';

        ctx.fillStyle = '#64748B';

        ctx.fillText(
          'đang mượn',
          width / 2,
          height / 2 + 24
        );

        ctx.restore();
      }
    };

    new Chart(canvas_2, {
      type: 'doughnut',

      plugins: [
        centerTextPlugin
      ],
      data: {
        labels: [
          'Đang được mượn',
          'Đặt trước',
          'Còn trong kho'
        ],

        datasets: [{
          data: [68, 20, 12],

          backgroundColor: [
            '#2952E3',
            '#E8963D',
            '#AAB2C5'
          ],

          borderWidth: 0
        }]
      },

      options: {
        cutout: '70%',
        plugins: {

          legend: {
            display: false
          }

        }
      }
    });
  }
}
