// Page header with an illustration, used on inner pages.
export default function Banner({ title, text, image }) {
  return (
    <section className="banner">
      <div><h2>{title}</h2><p>{text}</p></div>
      <img src={image} alt="" />
    </section>
  );
}
