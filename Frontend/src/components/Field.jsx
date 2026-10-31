export default function Field({ label, error, children }) {
  return <label className="field"><span>{label}</span>{children}{error && <em>{error}</em>}</label>;
}
