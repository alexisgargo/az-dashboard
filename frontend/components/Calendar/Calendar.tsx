"use client";
import * as React from "react";
import dayjs, { Dayjs } from "dayjs";
import { DemoContainer, DemoItem } from "@mui/x-date-pickers/internals/demo";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DateCalendar } from "@mui/x-date-pickers/DateCalendar";

export default function DateCalendarValue(
    props: Readonly<{
        setDate: (date: string) => void;
        date?: string;
    }>
) {
    const [value, setValue] = React.useState<Dayjs | null>(dayjs());

    const handleDateChange = (newValue: Dayjs | null) => {
        setValue(newValue);

        props.setDate(dayjs(newValue).format("YYYY-MM-DD"));
    };

    React.useEffect(() => {
        if (props.date) {
            handleDateChange(dayjs(props.date));
        }
    }, [props.date]);

    return (
        <LocalizationProvider dateAdapter={AdapterDayjs}>
            <DemoContainer components={["DateCalendar", "DateCalendar"]}>
                <DemoItem>
                    <DateCalendar value={value} onChange={handleDateChange} />
                </DemoItem>
            </DemoContainer>
        </LocalizationProvider>
    );
}
