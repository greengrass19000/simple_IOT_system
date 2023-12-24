import './App.css';
import { Line } from 'react-chartjs-2';
import Chart from 'chart.js/auto';
import React, { useState, useEffect } from 'react';
import { io } from 'socket.io-client';
import { Client } from '@stomp/stompjs';

function App() {

    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);

    const client = new Client({
      brokerURL: 'ws://localhost:8080/ws',
      onConnect: () => {
        client.publish({ destination: '/temperature', body: 'First Message' });
        client.subscribe('/topic/temperature', message =>
          console.log(`Received: ${message.body}`)
        );
      },
    });
  
    client.activate();

      
    const dataFE = {
      // labels: ['January', 'February', 'March', 'April', 'May'],
      labels: data.data ? data.data.map(a => {
        const tmp = new Date(a.timestamp);
        return tmp.getHours().toString() + " : " + tmp.getMinutes().toString();
      }) : [],
      datasets: [
        {
          // data: [65, 59, 80, 81, 56],
          data: data.data ? data.data.map(a => a.value) : [],
          label: 'Temperature sent from the IOT Node',
          fill: false,
          lineTension: 0.1,
          backgroundColor: 'rgba(75,192,192,0.4)',
          borderColor: 'rgba(75,192,192,1)',
          borderCapStyle: 'butt',
          borderDash: [],
          borderDashOffset: 0.0,
          borderJoinStyle: 'miter',
          pointBorderColor: 'rgba(75,192,192,1)',
          pointBackgroundColor: '#fff',
          pointBorderWidth: 1,
          pointHoverRadius: 5,
          pointHoverBackgroundColor: 'rgba(75,192,192,1)',
          pointHoverBorderColor: 'rgba(220,220,220,1)',
          pointHoverBorderWidth: 2,
          pointRadius: 1,
          pointHitRadius: 10,
        },
      ],
    };

    
  const options = {
    scales: {
      y: {
        beginAtZero: true,
        title: {
          display: true,
          text: 'Temperature (Celsius)'
        },
      },
      x: {
        ticks: {
            maxTicksLimit: 15
        },
        title: {
          display: true,
          text: 'Time'
        },
      }
    },
  };


  return (
    <div>
      <h2>Temperature Line Chart of IOT gateway</h2>
      <div style={{margin: '10em 10em 10em 10em'}}>
      <Line data={dataFE} options={options} />
      </div>
    </div>
  );
}

export default App;
