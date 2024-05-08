import { Options } from 'highcharts';

export const donutChartOptions: Options = {
  chart: {
    type: 'pie',
    plotShadow: false,
    backgroundColor: '#A8CCC9',
  },
  credits: {
    enabled: false,
  },
  plotOptions: {
    pie: {
      innerSize: '99%',
      borderWidth: 40,
      borderColor: undefined,
      slicedOffset: 20,
      dataLabels: {
        format: '<b>{point.name}</b>: {point.percentage:.1f}%',
        connectorWidth: 0,
      },
    },
  },
  title: {
    verticalAlign: 'middle',
    floating: true,
    text: 'Macros',
  },
  subtitle: {
    y: 225,
    floating: true,
    text: 'Total Calories: 0',
    style: {
      fontSize: '16px',
      fontWeight: 'bold',
    },
  },
  legend: {
    enabled: false,
  },
  series: [
    {
      type: 'pie',
      data: [
        { name: 'Carbohydrates', y: 0, color: '#00ffff' },
        { name: 'Fats', y: 0, color: '#ff00ff' },
        { name: 'Proteins', y: 0, color: '#ffa800' },
      ],
    },
  ],
};
