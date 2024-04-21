"use client";
import * as React from 'react';
import dayjs, { Dayjs } from 'dayjs';
import { DemoContainer, DemoItem } from '@mui/x-date-pickers/internals/demo';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DateCalendar } from '@mui/x-date-pickers/DateCalendar';

export default function DateCalendarValue() {
  const [value, setValue] = React.useState<Dayjs | null>(dayjs()); // Valor predeterminado: fecha actual


  return (
    <LocalizationProvider dateAdapter={AdapterDayjs}>
      <DemoContainer components={['DateCalendar', 'DateCalendar']}>
        <DemoItem>
          <DateCalendar value={value} onChange={setValue} />
        </DemoItem>
      </DemoContainer>
    </LocalizationProvider>
  );
};
