'use client'
import { title, subtitle } from "@/components/primitives";
import CalendarComponent from "@/components/Calendar/Calendar";
import { Button } from "@nextui-org/button";
import { useState } from "react";

export default function Home() {
  const [showCalendar, setShowCalendar] = useState(false);

  const toggleCalendar = () => {
    setShowCalendar(!showCalendar);
  };
  return (
    <section className="flex flex-col items-center justify-center gap-4 py-8 md:py-10">
      <div className="inline-block max-w-lg text-center justify-center">
        <h1 className={title()}>Make&nbsp;</h1>
        <h1 className={title({ color: "violet" })}>beautiful&nbsp;</h1>
        <br />
        <h1 className={title()}>
          websites regardless of your design experience.
        </h1>
        <h2 className={subtitle({ class: "mt-4" })}>
          Beautiful, fast and modern React UI library.
        </h2>
      </div>
      <div>
        <Button onClick={toggleCalendar}>CalendarComponent</Button>
        {showCalendar && <CalendarComponent />}
      </div>

    </section>
  );
}
