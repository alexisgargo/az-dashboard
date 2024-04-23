"use client";
import * as React from 'react';
import dayjs, { Dayjs } from 'dayjs'; 
import { DemoContainer, DemoItem } from '@mui/x-date-pickers/internals/demo';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DateCalendar } from '@mui/x-date-pickers/DateCalendar';
import { Button } from '@nextui-org/button';

export default function DateCalendarValue(setDate: any) {
  const [value, setValue] = React.useState<Dayjs | null>(dayjs()); 

  const handleDateChange = (newValue: Dayjs | null) => {
    setValue(newValue);
  
    setDate(newValue);

  };
  
  return (
   
      <LocalizationProvider dateAdapter={AdapterDayjs}>
        <DemoContainer components={['DateCalendar', 'DateCalendar']}>
          <DemoItem>
            <DateCalendar value={value} onChange={handleDateChange} />
          </DemoItem>
        </DemoContainer>
      </LocalizationProvider>
    
  );
}
