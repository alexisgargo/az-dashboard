"use client";
import * as React from 'react';
import dayjs, { Dayjs } from 'dayjs';
import axios from 'axios'; 
import { DemoContainer, DemoItem } from '@mui/x-date-pickers/internals/demo';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DateCalendar } from '@mui/x-date-pickers/DateCalendar';

export default function DateCalendarValue() {
  const [value, setValue] = React.useState<Dayjs | null>(dayjs()); 
  const [issue, setIssue] = React.useState<any[]>([]); 

  const getIssues = async (date: Dayjs, id_release: string) => {
    try {
      const response = await axios.get(`http://localhost:8080/az_dashboard/issues/${date.format('YYYY-MM-DD')}/${id_release}`);
      setIssue(response.data);
      console.log(issue);
    } catch (error) {
      console.error('Error fetching issues:', error);
    }
  };

  const handleDateChange = (newValue: Dayjs | null) => {
    setValue(newValue);
    const id_release = "1"; 
    if (newValue) {
      getIssues(newValue, id_release); 

    }
  };
  

  return (
    <LocalizationProvider dateAdapter={AdapterDayjs}>
      <DemoContainer components={['DateCalendar', 'DateCalendar']}>
        <DemoItem>
          <DateCalendar value={value} onChange={handleDateChange} />
        </DemoItem>
      </DemoContainer>
      <div>
        {issue.map(issue => (
    
          <div key={issue.issue_number}>
            <p>{issue.issue_description}</p>
          </div>
          
        ))}
      </div>
    </LocalizationProvider>
  );
}
