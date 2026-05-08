function ViralityBadge({ score }) {
  let level = 'Low';

  if (score > 500) level = 'High';
  else if (score > 200) level = 'Medium';

  return (
    <div className="virality-badge">
      <span>{level} Virality</span>
      <strong>{score}</strong>
    </div>
  );
}

export default ViralityBadge;