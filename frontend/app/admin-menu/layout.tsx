export default function adminMenuLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <section className="flex flex-col items-center justify-center gap-4">
      <div className="inline-block align-center w-full">
        {children}
      </div>
    </section>
  );
}
