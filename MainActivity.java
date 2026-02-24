<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
  <title>Melody Synth Go</title>
  <link rel="preconnect" href="https://fonts.googleapis.com"/>
  <link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=DM+Sans:wght@300;400;500;600&display=swap" rel="stylesheet"/>

  <!-- ═══════════════════════════════════════════════════
       ADSENSE — website version
       Replace ca-pub-XXXXXXXXXXXXXXXX with your real ID
  ════════════════════════════════════════════════════ -->
  <script async src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js?client=ca-pub-XXXXXXXXXXXXXXXX" crossorigin="anonymous"></script>

  <style>
    :root{
      --bg:#0a0a0f;--surface:#13131c;--card:#1c1c2a;--border:#2a2a3d;
      --accent:#ff4d6d;--gold:#ffc844;--teal:#00e5c3;
      --text:#f0f0f8;--muted:#6b6b88;--radius:18px;
      --font-head:'Bebas Neue',sans-serif;--font-body:'DM Sans',sans-serif;
    }
    *{margin:0;padding:0;box-sizing:border-box;-webkit-tap-highlight-color:transparent}
    body{background:var(--bg);color:var(--text);font-family:var(--font-body);min-height:100vh;overflow-x:hidden}
    body::before{content:'';position:fixed;inset:0;background-image:url("data:image/svg+xml,%3Csvg viewBox='0 0 256 256' xmlns='http://www.w3.org/2000/svg'%3E%3Cfilter id='n'%3E%3CfeTurbulence type='fractalNoise' baseFrequency='0.9' numOctaves='4' stitchTiles='stitch'/%3E%3C/filter%3E%3Crect width='100%25' height='100%25' filter='url(%23n)' opacity='0.04'/%3E%3C/svg%3E");pointer-events:none;z-index:0}

    /* Header */
    header{display:flex;align-items:center;justify-content:space-between;padding:20px 24px 12px;position:relative;z-index:10}
    .logo{font-family:var(--font-head);font-size:2rem;letter-spacing:2px;background:linear-gradient(120deg,var(--accent),var(--gold));-webkit-background-clip:text;-webkit-text-fill-color:transparent}
    .credits-badge{display:flex;align-items:center;gap:6px;background:var(--card);border:1px solid var(--border);border-radius:50px;padding:8px 16px;font-size:.95rem;font-weight:600;cursor:pointer}
    .credits-count{color:var(--gold)}
    .section-title{font-family:var(--font-head);font-size:1.4rem;letter-spacing:1.5px;color:var(--muted);padding:24px 24px 12px;text-transform:uppercase}

    /* Ad containers */
    .adsense-wrap{margin:0 16px 16px;min-height:90px;border-radius:12px;overflow:hidden;background:var(--surface);border:1px solid var(--border)}
    .adsense-wrap.hidden{display:none}
    .admob-wrap{margin:0 16px 16px;background:var(--surface);border:1px dashed var(--border);border-radius:12px;min-height:60px;display:flex;align-items:center;justify-content:center}
    .admob-wrap.hidden{display:none}
    .ad-label{font-size:.72rem;color:var(--muted);text-align:center;padding:10px}

    /* Platform badge */
    .platform-badge{margin:0 16px 14px;background:var(--surface);border:1px solid var(--border);border-radius:10px;padding:8px 14px;display:flex;align-items:center;gap:8px;font-size:.78rem;color:var(--muted)}
    .pdot{width:7px;height:7px;border-radius:50%}
    .pdot.web{background:var(--teal);box-shadow:0 0 5px var(--teal)}
    .pdot.app{background:var(--gold);box-shadow:0 0 5px var(--gold)}

    /* Record panel */
    .record-panel{margin:0 16px;background:linear-gradient(135deg,#1a0a1e,#0d1a2e);border:1px solid var(--border);border-radius:var(--radius);padding:28px 20px;text-align:center;position:relative;overflow:hidden}
    .record-panel::before{content:'';position:absolute;inset:0;background:radial-gradient(ellipse at 50% 0%,rgba(255,77,109,.15) 0%,transparent 70%);pointer-events:none}
    .record-btn{width:88px;height:88px;border-radius:50%;background:linear-gradient(145deg,var(--accent),#c0192f);border:none;cursor:pointer;display:flex;align-items:center;justify-content:center;margin:0 auto 16px;transition:transform .15s;position:relative;z-index:1}
    .record-btn.recording{animation:pulse 1.2s ease-out infinite}
    .record-btn:active{transform:scale(.94)}
    .record-btn svg{width:32px;height:32px;fill:white}
    @keyframes pulse{0%{box-shadow:0 0 0 0 rgba(255,77,109,.6)}70%{box-shadow:0 0 0 24px rgba(255,77,109,0)}100%{box-shadow:0 0 0 0 rgba(255,77,109,0)}}
    .record-label{font-size:.9rem;color:var(--muted);margin-bottom:6px;position:relative;z-index:1}
    .record-status{font-size:1rem;font-weight:600;min-height:1.5em;position:relative;z-index:1}
    .record-status.active{color:var(--accent)}
    .record-timer{font-family:var(--font-head);font-size:1.8rem;color:var(--accent);letter-spacing:3px;position:relative;z-index:1;min-height:2.2rem;margin-top:4px}
    .waveform{display:flex;align-items:center;justify-content:center;gap:3px;height:38px;margin:10px 0 0;position:relative;z-index:1}
    .wbar{width:4px;border-radius:4px;background:var(--accent);opacity:.4;height:6px}
    .waveform.active .wbar{animation:wv .6s ease-in-out infinite alternate;opacity:1}
    @keyframes wv{0%{height:4px}100%{height:30px}}
    .wbar:nth-child(2){animation-delay:.05s}.wbar:nth-child(3){animation-delay:.1s}.wbar:nth-child(4){animation-delay:.15s}.wbar:nth-child(5){animation-delay:.2s}.wbar:nth-child(6){animation-delay:.25s}.wbar:nth-child(7){animation-delay:.2s}.wbar:nth-child(8){animation-delay:.15s}.wbar:nth-child(9){animation-delay:.1s}.wbar:nth-child(10){animation-delay:.05s}

    /* Pitch display */
    .pitch-display{margin:8px auto 0;background:rgba(0,229,195,.06);border:1px solid rgba(0,229,195,.15);border-radius:10px;padding:10px 16px;font-size:.82rem;color:var(--muted);position:relative;z-index:1;min-height:38px;text-align:center}
    .pitch-note{font-family:var(--font-head);font-size:1.4rem;color:var(--teal);letter-spacing:2px}

    /* Instruments */
    .instruments-grid{display:grid;grid-template-columns:repeat(2,1fr);gap:12px;padding:0 16px}
    .instrument-card{background:var(--card);border:1px solid var(--border);border-radius:var(--radius);padding:18px 14px;cursor:pointer;transition:border-color .2s,transform .15s,background .2s;position:relative;overflow:hidden}
    .instrument-card:active{transform:scale(.97)}
    .instrument-card.selected{border-color:var(--teal);background:rgba(0,229,195,.08)}
    .instrument-card.selected::after{content:'✓';position:absolute;top:10px;right:12px;color:var(--teal);font-size:.85rem;font-weight:700}
    .inst-icon{font-size:2.2rem;margin-bottom:8px;display:block}
    .inst-name{font-weight:600;font-size:.95rem;margin-bottom:2px}
    .inst-cost{font-size:.78rem;color:var(--gold)}

    /* Convert */
    .convert-wrap{padding:20px 16px}
    .convert-btn{width:100%;background:linear-gradient(135deg,var(--teal),#008f7a);border:none;border-radius:var(--radius);color:#0a0a0f;font-family:var(--font-head);font-size:1.4rem;letter-spacing:2px;padding:18px;cursor:pointer;transition:opacity .2s,transform .15s;display:flex;align-items:center;justify-content:center;gap:10px}
    .convert-btn:disabled{opacity:.35;cursor:not-allowed}
    .convert-btn:not(:disabled):active{transform:scale(.98)}

    /* Result */
    .result-panel{margin:0 16px 20px;background:var(--card);border:1px solid var(--border);border-radius:var(--radius);padding:20px;display:none}
    .result-panel.visible{display:block}
    .result-header{display:flex;align-items:center;justify-content:space-between;margin-bottom:14px}
    .result-title{font-family:var(--font-head);font-size:1.3rem;letter-spacing:1px}
    .result-badge{background:rgba(0,229,195,.15);color:var(--teal);border-radius:50px;padding:4px 12px;font-size:.78rem;font-weight:600}
    audio{width:100%;border-radius:8px;margin-bottom:12px;accent-color:var(--teal)}
    .dl-btn{width:100%;padding:13px;border-radius:12px;font-weight:600;font-size:.9rem;cursor:pointer;transition:transform .15s;display:flex;align-items:center;justify-content:center;gap:8px;border:none;background:linear-gradient(135deg,var(--accent),#c0192f);color:white}
    .dl-btn:active{transform:scale(.97)}

    /* Earn */
    .earn-panel{margin:0 16px 20px;background:linear-gradient(135deg,#0f1a10,#0a1520);border:1px solid #2a3d2a;border-radius:var(--radius);padding:20px;display:flex;align-items:center;justify-content:space-between;gap:12px}
    .earn-info{flex:1}
    .earn-title{font-weight:700;font-size:1rem;margin-bottom:4px}
    .earn-desc{font-size:.82rem;color:var(--muted);line-height:1.4}
    .earn-btn{background:linear-gradient(135deg,var(--gold),#e0a800);border:none;border-radius:50px;color:#0a0a0f;font-weight:700;font-size:.9rem;padding:12px 20px;white-space:nowrap;cursor:pointer;display:flex;align-items:center;gap:6px}
    .earn-btn:active{transform:scale(.95)}

    /* Modals */
    .modal-overlay{position:fixed;inset:0;background:rgba(0,0,0,.85);z-index:100;display:none;align-items:center;justify-content:center;padding:20px;backdrop-filter:blur(4px)}
    .modal-overlay.open{display:flex}
    .modal{background:var(--card);border:1px solid var(--border);border-radius:var(--radius);padding:28px 24px;width:100%;max-width:360px;text-align:center;animation:min .25s ease}
    @keyframes min{from{transform:scale(.9) translateY(20px);opacity:0}to{transform:scale(1) translateY(0);opacity:1}}
    .modal-icon{font-size:3rem;margin-bottom:12px}
    .modal-title{font-family:var(--font-head);font-size:1.6rem;letter-spacing:1.5px;margin-bottom:8px}
    .modal-desc{color:var(--muted);font-size:.9rem;line-height:1.5;margin-bottom:24px}
    .modal-actions{display:flex;flex-direction:column;gap:10px}
    .mbtn{width:100%;padding:15px;border-radius:12px;font-weight:700;font-size:1rem;cursor:pointer;border:none}
    .mbtn:active{transform:scale(.97)}
    .mbtn-primary{background:linear-gradient(135deg,var(--gold),#e0a800);color:#0a0a0f}
    .mbtn-secondary{background:var(--surface);color:var(--muted);border:1px solid var(--border)}

    /* Ad loading / rewarded */
    .ad-loading{position:fixed;inset:0;background:#000;z-index:200;display:none;align-items:center;justify-content:center;flex-direction:column;gap:20px}
    .ad-loading.open{display:flex}
    .ad-spin{width:48px;height:48px;border:3px solid var(--border);border-top-color:var(--gold);border-radius:50%;animation:spin .8s linear infinite}
    @keyframes spin{to{transform:rotate(360deg)}}
    .ad-cd{font-family:var(--font-head);font-size:3rem;color:var(--gold);letter-spacing:3px}
    .ad-skip{background:var(--surface);color:var(--text);border:1px solid var(--border);border-radius:50px;padding:10px 24px;font-weight:600;font-size:.9rem;cursor:pointer;display:none}

    /* Processing */
    .proc-overlay{position:fixed;inset:0;background:rgba(0,0,0,.8);z-index:150;display:none;align-items:center;justify-content:center;flex-direction:column;gap:16px;backdrop-filter:blur(4px)}
    .proc-overlay.open{display:flex}
    .proc-ring{width:64px;height:64px;border:4px solid var(--border);border-top-color:var(--teal);border-radius:50%;animation:spin .9s linear infinite}
    .proc-text{font-family:var(--font-head);font-size:1.4rem;letter-spacing:2px;color:var(--teal)}
    .proc-sub{font-size:.82rem;color:var(--muted);text-align:center;max-width:260px;line-height:1.5}
    .proc-bar-wrap{width:220px;height:4px;background:var(--border);border-radius:4px;overflow:hidden}
    .proc-bar{height:100%;background:linear-gradient(90deg,var(--teal),var(--accent));border-radius:4px;width:0%;transition:width .2s}

    /* Toast */
    .toast{position:fixed;bottom:28px;left:50%;transform:translateX(-50%) translateY(20px);background:var(--card);border:1px solid var(--border);border-radius:50px;padding:12px 24px;font-size:.9rem;font-weight:500;opacity:0;transition:opacity .3s,transform .3s;z-index:300;white-space:nowrap;pointer-events:none;max-width:90vw;text-align:center}
    .toast.show{opacity:1;transform:translateX(-50%) translateY(0)}
    .toast.success{border-color:var(--teal);color:var(--teal)}
    .toast.error{border-color:var(--accent);color:var(--accent)}
    .bottom-spacer{height:48px}
  </style>
</head>
<body>

<!-- ══ HEADER ══ -->
<header>
  <div class="logo">🎵 Melody Synth</div>
  <div class="credits-badge" onclick="showEarnModal()">
    <span>🪙</span>
    <span class="credits-count" id="creditsDisplay">10</span>
    <span style="color:var(--muted);font-size:.8rem">&nbsp;credits</span>
  </div>
</header>

<!-- Platform indicator -->
<div class="platform-badge">
  <div class="pdot" id="pdot"></div>
  <span id="platformLabel">Loading...</span>
</div>

<!-- ══ TOP AD — auto switches AdSense/AdMob ══ -->
<div class="adsense-wrap hidden" id="adsTop">
  <ins class="adsbygoogle" style="display:block;width:100%;min-height:90px"
    data-ad-client="ca-pub-XXXXXXXXXXXXXXXX"
    data-ad-slot="XXXXXXXXXX"
    data-ad-format="auto"
    data-full-width-responsive="true"></ins>
</div>
<div class="admob-wrap hidden" id="admobTop">
  <div class="ad-label">📱 AdMob Banner<br/><small style="opacity:.5">Replace with your AdMob unit ID</small></div>
</div>

<!-- ══ RECORD ══ -->
<div class="section-title">Record Your Voice</div>
<div class="record-panel">
  <button class="record-btn" id="recordBtn" onclick="toggleRecord()">
    <svg id="micIcon" viewBox="0 0 24 24">
      <path d="M12 1a4 4 0 0 1 4 4v6a4 4 0 0 1-8 0V5a4 4 0 0 1 4-4zm7 10a1 1 0 0 1 2 0 9 9 0 0 1-8 8.94V21h2a1 1 0 0 1 0 2H9a1 1 0 0 1 0-2h2v-1.06A9 9 0 0 1 3 11a1 1 0 0 1 2 0 7 7 0 0 0 14 0z"/>
    </svg>
  </button>
  <div class="record-label">Tap to start recording your melody</div>
  <div class="record-status" id="recordStatus">Ready</div>
  <div class="record-timer" id="recordTimer"></div>
  <div class="waveform" id="waveform">
    <div class="wbar"></div><div class="wbar"></div><div class="wbar"></div>
    <div class="wbar"></div><div class="wbar"></div><div class="wbar"></div>
    <div class="wbar"></div><div class="wbar"></div><div class="wbar"></div>
    <div class="wbar"></div>
  </div>
  <div class="pitch-display" id="pitchDisplay">
    Hum a melody — notes will appear here 🎵
  </div>
</div>

<!-- ══ INSTRUMENTS ══ -->
<div class="section-title">Choose Instrument</div>
<div class="instruments-grid" id="instrumentsGrid"></div>

<!-- ══ CONVERT ══ -->
<div class="convert-wrap">
  <button class="convert-btn" id="convertBtn" onclick="startConvert()" disabled>
    ⚡ CONVERT — <span id="convertCostLabel">2 🪙</span>
  </button>
</div>

<!-- ══ MID AD ══ -->
<div class="adsense-wrap hidden" id="adsMid">
  <ins class="adsbygoogle" style="display:block;width:100%;min-height:90px"
    data-ad-client="ca-pub-XXXXXXXXXXXXXXXX"
    data-ad-slot="XXXXXXXXXX"
    data-ad-format="auto"
    data-full-width-responsive="true"></ins>
</div>
<div class="admob-wrap hidden" id="admobMid">
  <div class="ad-label">📱 AdMob Mid Banner</div>
</div>

<!-- ══ RESULT ══ -->
<div class="result-panel" id="resultPanel">
  <div class="result-header">
    <div class="result-title" id="resultTitle">Piano Version</div>
    <div class="result-badge">✓ Ready</div>
  </div>
  <audio id="audioPlayer" controls></audio>
  <button class="dl-btn" onclick="downloadResult()">⬇ Download — 1 🪙</button>
</div>

<!-- ══ EARN CREDITS ══ -->
<div class="section-title">Earn Free Credits</div>
<div class="earn-panel">
  <div class="earn-info">
    <div class="earn-title">Watch a short ad</div>
    <div class="earn-desc">Earn +5 credits instantly.<br/>Free — no purchase needed.</div>
  </div>
  <button class="earn-btn" onclick="showEarnModal()">🎬 Watch Ad</button>
</div>

<!-- ══ BOTTOM AD ══ -->
<div class="adsense-wrap hidden" id="adsBottom">
  <ins class="adsbygoogle" style="display:block;width:100%;min-height:90px"
    data-ad-client="ca-pub-XXXXXXXXXXXXXXXX"
    data-ad-slot="XXXXXXXXXX"
    data-ad-format="auto"
    data-full-width-responsive="true"></ins>
</div>
<div class="admob-wrap hidden" id="admobBottom">
  <div class="ad-label">📱 AdMob Bottom Banner</div>
</div>

<div class="bottom-spacer"></div>

<!-- Earn Modal -->
<div class="modal-overlay" id="earnModal">
  <div class="modal">
    <div class="modal-icon">🎬</div>
    <div class="modal-title">Watch & Earn</div>
    <div class="modal-desc">Watch a short ad to earn <strong style="color:var(--gold)">+5 credits</strong> instantly. No signup needed!</div>
    <div class="modal-actions">
      <button class="mbtn mbtn-primary" onclick="watchAd()">🪙 Watch Ad (+5 credits)</button>
      <button class="mbtn mbtn-secondary" onclick="closeModal('earnModal')">Maybe later</button>
    </div>
  </div>
</div>

<!-- No Credits Modal -->
<div class="modal-overlay" id="noCreditsModal">
  <div class="modal">
    <div class="modal-icon">😔</div>
    <div class="modal-title">Not Enough Credits</div>
    <div class="modal-desc" id="noCreditsDesc">Watch a short ad to earn free credits!</div>
    <div class="modal-actions">
      <button class="mbtn mbtn-primary" onclick="closeModal('noCreditsModal');showEarnModal()">🎬 Watch Ad & Earn</button>
      <button class="mbtn mbtn-secondary" onclick="closeModal('noCreditsModal')">Cancel</button>
    </div>
  </div>
</div>

<!-- Rewarded Ad Screen -->
<div class="ad-loading" id="adScreen">
  <div class="ad-spin"></div>
  <div id="adText" style="color:var(--muted);font-size:.9rem">Loading ad...</div>
  <div class="ad-cd" id="adCd" style="display:none"></div>
  <div style="display:flex;flex-direction:column;align-items:center;gap:10px;margin-top:16px">
    <button class="ad-skip" id="adSkip" onclick="adDone()">Skip Ad ›</button>
    <div style="font-size:.72rem;color:var(--muted)" id="adNote" style="display:none">Credits earned when ad completes.</div>
  </div>
</div>

<!-- Processing -->
<div class="proc-overlay" id="procOverlay">
  <div class="proc-ring"></div>
  <div class="proc-text">CONVERTING...</div>
  <div class="proc-sub" id="procSub">Analyzing your melody...</div>
  <div class="proc-bar-wrap"><div class="proc-bar" id="procBar"></div></div>
</div>

<div class="toast" id="toast"></div>

<script>
// ════════════════════════════════════════════════════════════
//  PLATFORM DETECTION
// ════════════════════════════════════════════════════════════
const IS_ANDROID = typeof window.Android !== 'undefined' || /wv|WebView/.test(navigator.userAgent);

function setupPlatform() {
  const dot = document.getElementById('pdot');
  const lbl = document.getElementById('platformLabel');
  if (IS_ANDROID) {
    dot.classList.add('app');
    lbl.textContent = '📱 Android App — AdMob Active';
    ['adsTop','adsMid','adsBottom'].forEach(id => document.getElementById(id).classList.add('hidden'));
    ['admobTop','admobMid','admobBottom'].forEach(id => document.getElementById(id).classList.remove('hidden'));
  } else {
    dot.classList.add('web');
    lbl.textContent = '🌐 Web Version — AdSense Active';
    ['admobTop','admobMid','admobBottom'].forEach(id => document.getElementById(id).classList.add('hidden'));
    ['adsTop','adsBottom'].forEach(id => {
      document.getElementById(id).classList.remove('hidden');
      try { (adsbygoogle = window.adsbygoogle || []).push({}); } catch(e) {}
    });
  }
}

// ════════════════════════════════════════════════════════════
//  INSTRUMENTS CONFIG
// ════════════════════════════════════════════════════════════
const INSTRUMENTS = [
  { id:'piano',   name:'Piano',   icon:'🎹', cost:2 },
  { id:'guitar',  name:'Guitar',  icon:'🎸', cost:2 },
  { id:'violin',  name:'Violin',  icon:'🎻', cost:3 },
  { id:'flute',   name:'Flute',   icon:'🪈', cost:2 },
  { id:'trumpet', name:'Trumpet', icon:'🎺', cost:3 },
  { id:'drums',   name:'Drums',   icon:'🥁', cost:2 },
  { id:'synth',   name:'Synth',   icon:'🎛️', cost:4 },
  { id:'bass',    name:'Bass',    icon:'🎸', cost:3 },
];

const DL_COST   = 1;
const AD_REWARD = 5;
const AD_SECS   = 15;

// ════════════════════════════════════════════════════════════
//  STATE
// ════════════════════════════════════════════════════════════
let credits      = parseInt(localStorage.getItem('msg_credits') || '10');
let selectedInst = null;
let detectedNotes= [];   // [{freq, time, duration}]
let resultBlob   = null;
let audioCtx     = null;
let analyser     = null;
let mediaStream  = null;
let recordingActive = false;
let recordTimer  = null;
let recordSecs   = 0;
let pitchTimer   = null;
let adTimer      = null;
let adSecs       = 0;
let noteHistory  = [];   // raw detected frequencies during recording

// ════════════════════════════════════════════════════════════
//  INIT
// ════════════════════════════════════════════════════════════
function init() {
  setupPlatform();
  renderInstruments();
  updateCredits();
}

function renderInstruments() {
  document.getElementById('instrumentsGrid').innerHTML = INSTRUMENTS.map(i => `
    <div class="instrument-card" id="inst-${i.id}" onclick="selectInst('${i.id}')">
      <span class="inst-icon">${i.icon}</span>
      <div class="inst-name">${i.name}</div>
      <div class="inst-cost">🪙 ${i.cost} credits</div>
    </div>`).join('');
}

function updateCredits() {
  document.getElementById('creditsDisplay').textContent = credits;
  localStorage.setItem('msg_credits', credits);
}

// ════════════════════════════════════════════════════════════
//  NOTE / PITCH DETECTION HELPERS
// ════════════════════════════════════════════════════════════

// Convert frequency to nearest musical note name
function freqToNote(freq) {
  if (!freq || freq < 60) return null;
  const noteNames = ['C','C#','D','D#','E','F','F#','G','G#','A','A#','B'];
  const n = 12 * Math.log2(freq / 440) + 69;
  const rounded = Math.round(n);
  const name = noteNames[((rounded % 12) + 12) % 12];
  const octave = Math.floor(rounded / 12) - 1;
  return { name: `${name}${octave}`, midi: rounded, freq };
}

// ════════════════════════════════════════════════════════════
//  IMPROVED YIN PITCH DETECTION
//  - Larger analysis window for more accuracy
//  - Stricter silence gate to ignore noise
//  - Octave error correction (common in voice detection)
// ════════════════════════════════════════════════════════════
function detectPitch(buffer, sampleRate) {
  const bufLen  = buffer.length;
  const yinLen  = Math.floor(bufLen / 2);
  const yin     = new Float32Array(yinLen);

  // RMS silence gate — ignore if too quiet
  let rms = 0;
  for (let i = 0; i < bufLen; i++) rms += buffer[i] * buffer[i];
  rms = Math.sqrt(rms / bufLen);
  if (rms < 0.015) return null;

  // Step 1: Difference function
  for (let tau = 1; tau < yinLen; tau++) {
    let sum = 0;
    for (let i = 0; i < yinLen; i++) {
      const d = buffer[i] - buffer[i + tau];
      sum += d * d;
    }
    yin[tau] = sum;
  }

  // Step 2: Cumulative mean normalised difference
  yin[0] = 1;
  let cumSum = 0;
  for (let tau = 1; tau < yinLen; tau++) {
    cumSum += yin[tau];
    yin[tau] = cumSum > 0 ? yin[tau] * tau / cumSum : 1;
  }

  // Step 3: Absolute threshold (0.12 = more strict = fewer wrong notes)
  const threshold = 0.12;
  let tau = -1;
  for (let t = 2; t < yinLen - 1; t++) {
    if (yin[t] < threshold) {
      // Take the deepest dip in this region
      while (t + 1 < yinLen && yin[t+1] < yin[t]) t++;
      tau = t;
      break;
    }
  }
  if (tau === -1) return null;

  // Step 4: Parabolic interpolation for sub-sample accuracy
  const x0 = tau > 0 ? tau - 1 : tau;
  const x2 = tau + 1 < yinLen ? tau + 1 : tau;
  let betterTau;
  if (x0 === tau) {
    betterTau = yin[tau] <= yin[x2] ? tau : x2;
  } else if (x2 === tau) {
    betterTau = yin[tau] <= yin[x0] ? tau : x0;
  } else {
    const s0 = yin[x0], s1 = yin[tau], s2 = yin[x2];
    const denom = 2 * (2 * s1 - s2 - s0);
    betterTau = denom !== 0 ? tau + (s2 - s0) / denom : tau;
  }

  let freq = sampleRate / betterTau;

  // Step 5: Octave error correction
  // Singing/humming often gets detected an octave too high or low
  // Clamp to human voice range (80–1200 Hz) and snap to nearest octave
  if (freq < 60)   return null;
  if (freq > 1400) return null;

  // If detected very high (>600Hz) but likely vocal, halve it
  // Human humming fundamental is typically 100–400Hz
  if (freq > 600 && freq < 1400) freq = freq / 2;

  return freq;
}

// ════════════════════════════════════════════════════════════
//  BUILD NOTE SEQUENCE from raw pitch history
//  - Larger median window for stability
//  - Groups by MIDI note number (not name) to handle octave drift
//  - Preserves original timing proportions
// ════════════════════════════════════════════════════════════
function buildNoteSequence(history, sampleRate) {
  if (history.length === 0) return [];
  const hopMs = (512 / 44100) * 1000; // ~11.6ms per frame

  // Step 1: Wider median filter (9-frame window) for stability
  const smoothed = history.map((f, i) => {
    const win = history.slice(Math.max(0, i-4), i+5).filter(Boolean);
    if (win.length < 3) return null;
    win.sort((a,b) => a-b);
    return win[Math.floor(win.length/2)];
  });

  // Step 2: Convert to MIDI notes
  const midiHistory = smoothed.map(freq => {
    if (!freq) return null;
    const note = freqToNote(freq);
    return note;
  });

  // Step 3: Group consecutive identical MIDI notes
  const notes = [];
  let cur = null, startMs = 0, count = 0;
  let freqAccum = 0; // accumulate freq for better average

  midiHistory.forEach((note, idx) => {
    if (note && cur && note.midi === cur.midi) {
      count++;
      freqAccum += note.freq;
    } else {
      if (cur && count >= 4) { // min ~46ms
        notes.push({
          freq: freqAccum / count, // average frequency (more accurate)
          name: cur.name,
          midi: cur.midi,
          startMs,
          durationMs: count * hopMs
        });
      }
      cur = note;
      startMs = idx * hopMs;
      count = 1;
      freqAccum = note ? note.freq : 0;
    }
  });
  if (cur && count >= 4) {
    notes.push({ freq: freqAccum/count, name:cur.name, midi:cur.midi, startMs, durationMs: count * hopMs });
  }

  // Step 4: Filter very short notes and merge duplicates that are adjacent
  const filtered = notes.filter(n => n.durationMs >= 80);

  // Step 5: Re-space timing proportionally starting from 0
  if (filtered.length === 0) return [];
  const firstStart = filtered[0].startMs;
  filtered.forEach(n => { n.startMs -= firstStart; });

  return filtered;
}

// ════════════════════════════════════════════════════════════
//  RECORDING
// ════════════════════════════════════════════════════════════
async function toggleRecord() {
  if (recordingActive) { stopRecording(); return; }
  try {
    mediaStream = await navigator.mediaDevices.getUserMedia({ audio: true, video: false });
    startRecording();
  } catch(e) {
    showToast('❌ Microphone access denied. Allow mic in your browser settings.', 'error');
  }
}

function startRecording() {
  recordingActive = true;
  recordSecs = 0;
  noteHistory = [];
  detectedNotes = [];

  // Audio context for pitch detection
  audioCtx  = new (window.AudioContext || window.webkitAudioContext)();
  const src = audioCtx.createMediaStreamSource(mediaStream);
  analyser  = audioCtx.createAnalyser();
  analyser.fftSize = 2048;
  src.connect(analyser);

  // UI
  document.getElementById('recordBtn').classList.add('recording');
  document.getElementById('recordStatus').textContent = 'Recording... tap again to stop';
  document.getElementById('recordStatus').classList.add('active');
  document.getElementById('waveform').classList.add('active');
  document.getElementById('micIcon').innerHTML = '<rect x="6" y="6" width="12" height="12" rx="2"/>';
  document.getElementById('pitchDisplay').innerHTML = 'Listening for melody...';

  // Timer display
  recordTimer = setInterval(() => {
    recordSecs++;
    const m = String(Math.floor(recordSecs/60)).padStart(2,'0');
    const s = String(recordSecs%60).padStart(2,'0');
    document.getElementById('recordTimer').textContent = `${m}:${s}`;
    if (recordSecs >= 30) stopRecording();
  }, 1000);

  // Pitch detection loop (~every 50ms)
  const buf = new Float32Array(analyser.fftSize);
  pitchTimer = setInterval(() => {
    analyser.getFloatTimeDomainData(buf);

    // Check if there's signal (not silence)
    const rms = Math.sqrt(buf.reduce((s,v) => s + v*v, 0) / buf.length);
    if (rms < 0.01) { noteHistory.push(null); return; }

    const freq = detectPitch(buf, audioCtx.sampleRate);
    noteHistory.push(freq && freq > 60 && freq < 2000 ? freq : null);

    // Show live note
    if (freq && freq > 60) {
      const note = freqToNote(freq);
      if (note) {
        document.getElementById('pitchDisplay').innerHTML =
          `<span class="pitch-note">${note.name}</span> &nbsp; <span style="color:var(--muted);font-size:.75rem">${Math.round(freq)}Hz</span>`;
      }
    }
  }, 50);

  showToast('🎙️ Recording started — hum your melody!', '');
}

function stopRecording() {
  if (!recordingActive) return;
  recordingActive = false;
  clearInterval(recordTimer);
  clearInterval(pitchTimer);

  document.getElementById('recordTimer').textContent = '';
  document.getElementById('recordBtn').classList.remove('recording');
  document.getElementById('recordStatus').classList.remove('active');
  document.getElementById('waveform').classList.remove('active');
  document.getElementById('micIcon').innerHTML = '<path d="M12 1a4 4 0 0 1 4 4v6a4 4 0 0 1-8 0V5a4 4 0 0 1 4-4zm7 10a1 1 0 0 1 2 0 9 9 0 0 1-8 8.94V21h2a1 1 0 0 1 0 2H9a1 1 0 0 1 0-2h2v-1.06A9 9 0 0 1 3 11a1 1 0 0 1 2 0 7 7 0 0 0 14 0z"/>';

  if (mediaStream) { mediaStream.getTracks().forEach(t => t.stop()); }
  if (audioCtx)    { audioCtx.close(); }

  // Build note sequence from raw pitch history
  detectedNotes = buildNoteSequence(noteHistory, 44100);

  if (detectedNotes.length === 0) {
    document.getElementById('recordStatus').textContent = 'No melody detected — try again louder';
    document.getElementById('pitchDisplay').innerHTML = 'Try humming closer to the microphone 🎤';
    showToast('⚠️ No notes detected. Try humming louder.', 'error');
    return;
  }

  const noteList = [...new Set(detectedNotes.map(n => n.name))].join(' · ');
  document.getElementById('recordStatus').textContent = `✓ Melody captured (${detectedNotes.length} notes)`;
  document.getElementById('pitchDisplay').innerHTML =
    `<span style="color:var(--teal);font-size:.8rem">Detected: </span> ${noteList}`;

  checkReady();
  showToast(`✅ ${detectedNotes.length} notes captured! Now pick an instrument.`, 'success');
}

// ════════════════════════════════════════════════════════════
//  INSTRUMENT SELECT
// ════════════════════════════════════════════════════════════
function selectInst(id) {
  if (selectedInst) document.getElementById('inst-'+selectedInst).classList.remove('selected');
  selectedInst = id;
  document.getElementById('inst-'+id).classList.add('selected');
  const inst = INSTRUMENTS.find(i => i.id === id);
  document.getElementById('convertCostLabel').textContent = `${inst.cost} 🪙`;
  checkReady();
}
function checkReady() {
  document.getElementById('convertBtn').disabled = !(detectedNotes.length > 0 && selectedInst);
}

// ════════════════════════════════════════════════════════════
//  CONVERT — Web Audio synthesis (no API, works offline)
// ════════════════════════════════════════════════════════════
async function startConvert() {
  if (!detectedNotes.length || !selectedInst) return;
  const inst = INSTRUMENTS.find(i => i.id === selectedInst);

  if (credits < inst.cost) {
    document.getElementById('noCreditsDesc').textContent =
      `You need ${inst.cost} credits for ${inst.name}. Watch an ad to earn free credits!`;
    openModal('noCreditsModal'); return;
  }

  credits -= inst.cost;
  updateCredits();

  document.getElementById('procSub').textContent = `Synthesizing ${inst.name} version...`;
  document.getElementById('procBar').style.width = '0%';
  document.getElementById('procOverlay').classList.add('open');

  // Small delay so the UI updates before heavy audio work
  await new Promise(r => setTimeout(r, 80));

  try {
    const blob = await synthesizeMelody(detectedNotes, inst);
    resultBlob = blob;

    document.getElementById('procBar').style.width = '100%';
    await new Promise(r => setTimeout(r, 200));
    document.getElementById('procOverlay').classList.remove('open');

    showResult(inst, blob);

    // Show mid AdSense after conversion on web
    if (!IS_ANDROID) {
      const mid = document.getElementById('adsMid');
      if (mid.classList.contains('hidden')) {
        mid.classList.remove('hidden');
        try { (adsbygoogle = window.adsbygoogle || []).push({}); } catch(e) {}
      }
    } else {
      document.getElementById('admobMid').classList.remove('hidden');
    }

  } catch(e) {
    credits += inst.cost; updateCredits();
    document.getElementById('procOverlay').classList.remove('open');
    showToast(`❌ Error: ${e.message}`, 'error');
  }
}

// ════════════════════════════════════════════════════════════
//  SNAP FREQ TO NEAREST MUSICAL NOTE (ensure tuning is correct)
// ════════════════════════════════════════════════════════════
function snapToNote(freq) {
  // A4 = 440Hz, equal temperament
  const midi = 12 * Math.log2(freq / 440) + 69;
  const rounded = Math.round(midi);
  return 440 * Math.pow(2, (rounded - 69) / 12);
}

// ════════════════════════════════════════════════════════════
//  SYNTHESIZE MELODY — physically modelled per instrument
// ════════════════════════════════════════════════════════════
async function synthesizeMelody(notes, inst) {
  const SR       = 44100;
  const lastNote = notes[notes.length - 1];
  const totalSec = (lastNote.startMs + lastNote.durationMs) / 1000 + 1.5;
  const totalSmp = Math.ceil(totalSec * SR);

  const offCtx = new OfflineAudioContext(2, totalSmp, SR);

  // Master chain: gain → compressor → reverb → output
  const master = offCtx.createGain();
  master.gain.value = 0.75;

  const comp = offCtx.createDynamicsCompressor();
  comp.threshold.value = -18; comp.knee.value = 10;
  comp.ratio.value = 4; comp.attack.value = 0.003; comp.release.value = 0.1;
  master.connect(comp);

  // Stereo reverb
  const reverb  = offCtx.createConvolver();
  reverb.buffer = makeReverb(offCtx, 1.8, 3.0);
  const dry = offCtx.createGain(); dry.gain.value = 0.72;
  const wet = offCtx.createGain(); wet.gain.value = 0.28;
  comp.connect(dry);   dry.connect(offCtx.destination);
  comp.connect(reverb); reverb.connect(wet); wet.connect(offCtx.destination);

  let step = 0;
  for (const note of notes) {
    step++;
    document.getElementById('procBar').style.width = (step / notes.length * 90) + '%';
    const t   = note.startMs / 1000;
    const dur = Math.max(note.durationMs / 1000, 0.07);
    // Snap to exact musical pitch — fixes out-of-tune issue
    const f   = snapToNote(note.freq);
    buildInstrumentNote(offCtx, master, inst.id, f, t, dur);
  }

  const rendered = await offCtx.startRendering();
  return audioBufferToWav(rendered);
}

// ════════════════════════════════════════════════════════════
//  PER-INSTRUMENT PHYSICAL MODELLING
// ════════════════════════════════════════════════════════════
function buildInstrumentNote(ctx, dest, id, freq, t, dur) {
  switch(id) {
    case 'piano':   synthPiano(ctx, dest, freq, t, dur);   break;
    case 'guitar':  synthGuitar(ctx, dest, freq, t, dur);  break;
    case 'violin':  synthViolin(ctx, dest, freq, t, dur);  break;
    case 'flute':   synthFlute(ctx, dest, freq, t, dur);   break;
    case 'trumpet': synthTrumpet(ctx, dest, freq, t, dur); break;
    case 'drums':   synthDrums(ctx, dest, freq, t, dur);   break;
    case 'synth':   synthSynth(ctx, dest, freq, t, dur);   break;
    case 'bass':    synthBass(ctx, dest, freq, t, dur);    break;
  }
}

// ── PIANO: bright attack, exponential decay, inharmonic partials ──
function synthPiano(ctx, dest, freq, t, dur) {
  const g = ctx.createGain();
  // Piano partials: slightly stretched (inharmonic) for realism
  const partials = [
    {f:1.000, a:1.00}, {f:2.001, a:0.50}, {f:3.003, a:0.28},
    {f:4.006, a:0.15}, {f:5.010, a:0.08}, {f:6.015, a:0.04},
  ];
  partials.forEach(({f, a}) => {
    const o = ctx.createOscillator();
    const og = ctx.createGain();
    o.type = 'sine';
    o.frequency.value = freq * f;
    og.gain.value = a;
    o.connect(og); og.connect(g);
    o.start(t); o.stop(t + dur + 1.2);
  });
  // Strike transient — sharp bright click at attack
  addTransient(ctx, g, t, 0.003, freq * 8, 400);
  // ADSR: fast attack, exponential decay (piano-like)
  const env = g.gain;
  env.setValueAtTime(0, t);
  env.linearRampToValueAtTime(0.9, t + 0.008);
  env.setTargetAtTime(0.3, t + 0.008, 0.15);
  env.setTargetAtTime(0.0, t + dur * 0.4, 0.3);
  g.connect(dest);
}

// ── GUITAR: Karplus-Strong plucked string simulation ──
function synthGuitar(ctx, dest, freq, t, dur) {
  const SR = ctx.sampleRate;
  const period = Math.round(SR / freq);
  const bufLen = Math.max(period * 2, 256);

  // Seed buffer with white noise (the pluck)
  const noiseBuf = ctx.createBuffer(1, bufLen, SR);
  const nd = noiseBuf.getChannelData(0);
  for (let i = 0; i < bufLen; i++) nd[i] = Math.random() * 2 - 1;

  const noiseSrc = ctx.createBufferSource();
  noiseSrc.buffer = noiseBuf;

  // Low-pass filter simulates string dampening
  const lp = ctx.createBiquadFilter();
  lp.type = 'lowpass';
  lp.frequency.value = freq * 6;
  lp.Q.value = 0.5;

  // Bandpass to emphasize guitar body resonance
  const bp = ctx.createBiquadFilter();
  bp.type = 'bandpass';
  bp.frequency.value = freq * 2.5;
  bp.Q.value = 2;

  const g = ctx.createGain();
  g.gain.setValueAtTime(0.8, t);
  g.gain.exponentialRampToValueAtTime(0.001, t + Math.min(dur + 0.8, 2.5));

  noiseSrc.connect(lp); lp.connect(bp); bp.connect(g); g.connect(dest);
  noiseSrc.start(t); noiseSrc.stop(t + 0.05);

  // Add sustained fundamental using oscillator
  const osc = ctx.createOscillator();
  const oscG = ctx.createGain();
  osc.type = 'sawtooth';
  osc.frequency.value = freq;
  const oscLP = ctx.createBiquadFilter();
  oscLP.type = 'lowpass'; oscLP.frequency.value = freq * 5;
  oscG.gain.setValueAtTime(0, t);
  oscG.gain.linearRampToValueAtTime(0.4, t + 0.01);
  oscG.gain.exponentialRampToValueAtTime(0.001, t + Math.min(dur + 0.5, 2.0));
  osc.connect(oscLP); oscLP.connect(oscG); oscG.connect(dest);
  osc.start(t); osc.stop(t + dur + 1.0);
}

// ── VIOLIN: bowed string — sawtooth + vibrato + bow noise ──
function synthViolin(ctx, dest, freq, t, dur) {
  const g = ctx.createGain();

  // Bowed tone: rich sawtooth harmonics
  const partials = [
    {f:1, a:1.0}, {f:2, a:0.7}, {f:3, a:0.5},
    {f:4, a:0.35},{f:5, a:0.25},{f:6, a:0.15},{f:7, a:0.08},
  ];
  const oscs = partials.map(({f, a}) => {
    const o = ctx.createOscillator();
    const og = ctx.createGain();
    o.type = 'sawtooth';
    o.frequency.value = freq * f;
    og.gain.value = a * 0.3;
    o.connect(og); og.connect(g);
    o.start(t); o.stop(t + dur + 0.5);
    return o;
  });

  // Vibrato — LFO on frequency (real violin vibrato ~5.5Hz)
  const lfo = ctx.createOscillator();
  const lfoG = ctx.createGain();
  lfo.frequency.value = 5.5;
  lfoG.gain.value = 0; // starts silent, ramps in after bow settles
  lfo.connect(lfoG);
  // Apply vibrato to fundamental
  lfoG.connect(oscs[0].frequency);
  lfoG.gain.setValueAtTime(0, t + 0.1);
  lfoG.gain.linearRampToValueAtTime(freq * 0.012, t + 0.25); // ~1.2% pitch wobble
  lfo.start(t); lfo.stop(t + dur + 0.5);

  // Bow noise layer (adds breathiness)
  const noiseAmt = ctx.createGain();
  noiseAmt.gain.value = 0.04;
  const noiseHP = ctx.createBiquadFilter();
  noiseHP.type = 'highpass'; noiseHP.frequency.value = freq * 3;
  addNoise(ctx, noiseHP, t, dur + 0.3);
  noiseHP.connect(noiseAmt); noiseAmt.connect(g);

  // Highpass to remove muddiness
  const hp = ctx.createBiquadFilter();
  hp.type = 'highpass'; hp.frequency.value = 200;
  g.connect(hp);

  // ADSR: slow bow attack
  const env = g.gain;
  env.setValueAtTime(0, t);
  env.linearRampToValueAtTime(0.9, t + 0.09);
  env.setValueAtTime(0.85, t + dur - 0.05);
  env.linearRampToValueAtTime(0, t + dur + 0.15);

  hp.connect(dest);
}

// ── FLUTE: sine + breath noise + embouchure resonance ──
function synthFlute(ctx, dest, freq, t, dur) {
  const g = ctx.createGain();

  // Pure flute tone: sine + 2nd harmonic + slight 3rd
  [[1, 1.0],[2, 0.25],[3, 0.06]].forEach(([f, a]) => {
    const o = ctx.createOscillator();
    const og = ctx.createGain();
    o.type = 'sine'; o.frequency.value = freq * f; og.gain.value = a;
    o.connect(og); og.connect(g);
    o.start(t); o.stop(t + dur + 0.4);
  });

  // Breath noise — high frequency bandpass
  const breathG = ctx.createGain(); breathG.gain.value = 0.06;
  const breathBP = ctx.createBiquadFilter();
  breathBP.type = 'bandpass'; breathBP.frequency.value = freq * 4; breathBP.Q.value = 0.8;
  addNoise(ctx, breathBP, t, dur + 0.3);
  breathBP.connect(breathG); breathG.connect(g);

  // Vibrato ~5Hz
  const lfo = ctx.createOscillator();
  const lfoG = ctx.createGain();
  lfo.frequency.value = 5.0;
  lfoG.gain.value = freq * 0.008;
  lfo.connect(lfoG);
  const fundamental = ctx.createOscillator();
  fundamental.type = 'sine'; fundamental.frequency.value = freq;
  lfoG.connect(fundamental.frequency);
  lfo.start(t + 0.15); lfo.stop(t + dur + 0.3);

  // ADSR: gentle attack, sustained
  const env = g.gain;
  env.setValueAtTime(0, t);
  env.linearRampToValueAtTime(0.8, t + 0.06);
  env.setValueAtTime(0.75, t + dur - 0.05);
  env.linearRampToValueAtTime(0, t + dur + 0.2);

  g.connect(dest);
}

// ── TRUMPET: bright harmonics, sharp attack, brassy distortion ──
function synthTrumpet(ctx, dest, freq, t, dur) {
  const g = ctx.createGain();

  // Brass harmonics — odd+even, strong upper partials
  const partials = [
    {f:1, a:0.7}, {f:2, a:1.0}, {f:3, a:0.9},
    {f:4, a:0.7}, {f:5, a:0.5}, {f:6, a:0.3},
    {f:7, a:0.15},{f:8, a:0.08},
  ];
  partials.forEach(({f, a}) => {
    const o = ctx.createOscillator();
    const og = ctx.createGain();
    o.type = f <= 2 ? 'square' : 'sawtooth';
    o.frequency.value = freq * f;
    og.gain.value = a * 0.2;
    o.connect(og); og.connect(g);
    o.start(t); o.stop(t + dur + 0.3);
  });

  // Brassy waveshaper distortion
  const wave = ctx.createWaveShaper();
  wave.curve = makeBrassCurve(256);
  wave.oversample = '2x';
  g.connect(wave);

  // Low-pass to tame harshness
  const lp = ctx.createBiquadFilter();
  lp.type = 'lowpass'; lp.frequency.value = freq * 12;
  wave.connect(lp);

  // ADSR: quick punch
  const env = g.gain;
  env.setValueAtTime(0, t);
  env.linearRampToValueAtTime(1.0, t + 0.015);
  env.linearRampToValueAtTime(0.7, t + 0.06);
  env.setValueAtTime(0.65, t + dur - 0.04);
  env.linearRampToValueAtTime(0, t + dur + 0.12);

  lp.connect(dest);
}

function makeBrassCurve(n) {
  const curve = new Float32Array(n);
  for (let i = 0; i < n; i++) {
    const x = (i * 2) / n - 1;
    curve[i] = (Math.PI + 120) * x / (Math.PI + 120 * Math.abs(x));
  }
  return curve;
}

// ── DRUMS: layered kick + snare depending on detected pitch ──
function synthDrums(ctx, dest, freq, t, dur) {
  // High pitch → hihat/snare, low pitch → kick
  if (freq > 300) {
    synthSnare(ctx, dest, t);
  } else {
    synthKick(ctx, dest, t, freq);
  }
}

function synthKick(ctx, dest, t, freq) {
  const g = ctx.createGain();
  // Pitch sweep: starts high, drops fast (classic kick)
  const o = ctx.createOscillator();
  o.type = 'sine';
  o.frequency.setValueAtTime(freq * 3, t);
  o.frequency.exponentialRampToValueAtTime(freq * 0.5, t + 0.08);
  // Sub layer
  const sub = ctx.createOscillator();
  sub.type = 'sine'; sub.frequency.value = freq * 0.5;
  const subG = ctx.createGain(); subG.gain.value = 0.6;
  sub.connect(subG); subG.connect(g);
  // Noise transient
  addTransient(ctx, g, t, 0.006, 3000, 800);
  // Envelope
  g.gain.setValueAtTime(1.0, t);
  g.gain.exponentialRampToValueAtTime(0.001, t + 0.4);
  o.connect(g); g.connect(dest);
  o.start(t); o.stop(t + 0.45);
  sub.start(t); sub.stop(t + 0.45);
}

function synthSnare(ctx, dest, t) {
  // Tone layer
  const toneG = ctx.createGain();
  const o1 = ctx.createOscillator(); o1.frequency.value = 185;
  const o2 = ctx.createOscillator(); o2.frequency.value = 230;
  o1.type = o2.type = 'triangle';
  o1.connect(toneG); o2.connect(toneG);
  toneG.gain.setValueAtTime(0.6, t);
  toneG.gain.exponentialRampToValueAtTime(0.001, t + 0.18);
  toneG.connect(dest); o1.start(t); o1.stop(t+0.2); o2.start(t); o2.stop(t+0.2);
  // Noise layer (snare rattle)
  const noiseG = ctx.createGain();
  const hp = ctx.createBiquadFilter(); hp.type='highpass'; hp.frequency.value=1800;
  addNoise(ctx, hp, t, 0.2);
  hp.connect(noiseG); noiseG.connect(dest);
  noiseG.gain.setValueAtTime(0.8, t);
  noiseG.gain.exponentialRampToValueAtTime(0.001, t + 0.22);
}

// ── SYNTH: detuned oscillators + filter sweep ──
function synthSynth(ctx, dest, freq, t, dur) {
  const g = ctx.createGain();
  // 3 slightly detuned saws for that classic synth thickness
  [-6, 0, 6].forEach(cents => {
    const o = ctx.createOscillator();
    const og = ctx.createGain();
    o.type = 'sawtooth';
    o.frequency.value = freq * Math.pow(2, cents / 1200);
    og.gain.value = 0.33;
    o.connect(og); og.connect(g);
    o.start(t); o.stop(t + dur + 0.6);
  });

  // Filter sweep (cutoff opens up on attack)
  const lp = ctx.createBiquadFilter();
  lp.type = 'lowpass'; lp.Q.value = 8;
  lp.frequency.setValueAtTime(freq * 0.5, t);
  lp.frequency.exponentialRampToValueAtTime(freq * 10, t + 0.12);
  lp.frequency.exponentialRampToValueAtTime(freq * 3, t + 0.5);

  // ADSR
  const env = g.gain;
  env.setValueAtTime(0, t);
  env.linearRampToValueAtTime(0.85, t + 0.02);
  env.linearRampToValueAtTime(0.5, t + 0.15);
  env.setValueAtTime(0.5, t + dur - 0.05);
  env.linearRampToValueAtTime(0, t + dur + 0.4);

  g.connect(lp); lp.connect(dest);
}

// ── BASS: warm plucked bass with body resonance ──
function synthBass(ctx, dest, freq, t, dur) {
  const g = ctx.createGain();

  // Low fundamental + 2nd harmonic only (warm, not harsh)
  [[1,1.0],[2,0.4],[3,0.15]].forEach(([f,a]) => {
    const o = ctx.createOscillator();
    const og = ctx.createGain();
    o.type = f === 1 ? 'triangle' : 'sawtooth';
    o.frequency.value = freq * f;
    og.gain.value = a;
    o.connect(og); og.connect(g);
    o.start(t); o.stop(t + dur + 0.8);
  });

  // Low-pass to keep it warm and round
  const lp = ctx.createBiquadFilter();
  lp.type = 'lowpass'; lp.frequency.value = freq * 4; lp.Q.value = 1;

  // Pluck transient
  addTransient(ctx, g, t, 0.004, freq * 5, 300);

  // ADSR
  const env = g.gain;
  env.setValueAtTime(0, t);
  env.linearRampToValueAtTime(0.9, t + 0.008);
  env.linearRampToValueAtTime(0.4, t + 0.1);
  env.setValueAtTime(0.35, t + dur - 0.05);
  env.linearRampToValueAtTime(0, t + dur + 0.5);

  g.connect(lp); lp.connect(dest);
}

// ════════════════════════════════════════════════════════════
//  UTILITY AUDIO HELPERS
// ════════════════════════════════════════════════════════════

// Short noise transient (attack click/pluck)
function addTransient(ctx, dest, t, dur, centerFreq, q) {
  const bufLen = Math.ceil(ctx.sampleRate * dur);
  const buf = ctx.createBuffer(1, bufLen, ctx.sampleRate);
  const d = buf.getChannelData(0);
  for (let i = 0; i < bufLen; i++) d[i] = (Math.random() * 2 - 1) * (1 - i/bufLen);
  const src = ctx.createBufferSource(); src.buffer = buf;
  const bp = ctx.createBiquadFilter();
  bp.type = 'bandpass'; bp.frequency.value = centerFreq; bp.Q.value = q / centerFreq;
  const tg = ctx.createGain(); tg.gain.value = 0.5;
  src.connect(bp); bp.connect(tg); tg.connect(dest);
  src.start(t); src.stop(t + dur + 0.01);
}

// Sustained noise source for breath/bow effects
function addNoise(ctx, dest, t, dur) {
  const bufLen = Math.ceil(ctx.sampleRate * Math.min(dur + 0.1, 3.0));
  const buf = ctx.createBuffer(1, bufLen, ctx.sampleRate);
  const d = buf.getChannelData(0);
  for (let i = 0; i < bufLen; i++) d[i] = Math.random() * 2 - 1;
  const src = ctx.createBufferSource(); src.buffer = buf;
  src.connect(dest);
  src.start(t); src.stop(t + dur + 0.05);
}

// High-quality reverb impulse
function makeReverb(ctx, duration, decay) {
  const len = Math.ceil(ctx.sampleRate * duration);
  const buf = ctx.createBuffer(2, len, ctx.sampleRate);
  for (let c = 0; c < 2; c++) {
    const d = buf.getChannelData(c);
    for (let i = 0; i < len; i++) {
      // Exponential decay + pre-delay + early reflections
      const env = Math.pow(1 - i / len, decay);
      d[i] = (Math.random() * 2 - 1) * env;
      // Add some early reflections
      if (i < ctx.sampleRate * 0.05) d[i] *= 1.5;
    }
  }
  return buf;
}

// ════════════════════════════════════════════════════════════
//  AudioBuffer → WAV Blob  (PCM 16-bit stereo)
// ════════════════════════════════════════════════════════════
function audioBufferToWav(buffer) {
  const nCh = buffer.numberOfChannels;
  const SR  = buffer.sampleRate;
  const nSm = buffer.length;
  const bps = 2;
  const blk = nCh * bps;
  const byr = SR * blk;
  const dsz = nSm * blk;
  const ab  = new ArrayBuffer(44 + dsz);
  const v   = new DataView(ab);
  const ws  = (o, s) => { for (let i=0;i<s.length;i++) v.setUint8(o+i, s.charCodeAt(i)); };

  ws(0,'RIFF'); v.setUint32(4,36+dsz,true); ws(8,'WAVE');
  ws(12,'fmt '); v.setUint32(16,16,true); v.setUint16(20,1,true);
  v.setUint16(22,nCh,true); v.setUint32(24,SR,true); v.setUint32(28,byr,true);
  v.setUint16(32,blk,true); v.setUint16(34,16,true);
  ws(36,'data'); v.setUint32(40,dsz,true);

  let offset = 44;
  for (let i = 0; i < nSm; i++) {
    for (let c = 0; c < nCh; c++) {
      const s = Math.max(-1, Math.min(1, buffer.getChannelData(c)[i]));
      v.setInt16(offset, s < 0 ? s * 32768 : s * 32767, true);
      offset += 2;
    }
  }
  return new Blob([ab], { type: 'audio/wav' });
}

// ════════════════════════════════════════════════════════════
//  RESULT + DOWNLOAD
// ════════════════════════════════════════════════════════════
function showResult(inst, blob) {
  const url = URL.createObjectURL(blob);
  document.getElementById('audioPlayer').src = url;
  document.getElementById('resultTitle').textContent = `${inst.icon} ${inst.name} Version`;
  document.getElementById('resultPanel').classList.add('visible');
  document.getElementById('resultPanel').scrollIntoView({ behavior:'smooth', block:'nearest' });
  showToast(`🎶 ${inst.name} ready! Tap play ▶`, 'success');
}

function downloadResult() {
  if (!resultBlob) return;
  if (credits < DL_COST) {
    document.getElementById('noCreditsDesc').textContent = `You need ${DL_COST} credit to download. Watch an ad!`;
    openModal('noCreditsModal'); return;
  }
  credits -= DL_COST; updateCredits();
  const inst = INSTRUMENTS.find(i => i.id === selectedInst);
  const a    = document.createElement('a');
  a.href     = URL.createObjectURL(resultBlob);
  a.download = `melody-synth-${inst?.id||'output'}.wav`;
  a.click();
  showToast('⬇ Downloading...', 'success');
}

// ════════════════════════════════════════════════════════════
//  ADS / EARN CREDITS
// ════════════════════════════════════════════════════════════
function showEarnModal() { openModal('earnModal'); }

function watchAd() {
  closeModal('earnModal');
  if (IS_ANDROID && window.Android && typeof window.Android.showRewardedAd === 'function') {
    window.Android.showRewardedAd();
  } else {
    simulateAd();
  }
}

window.onAdRewarded = function() {
  credits += AD_REWARD; updateCredits();
  showToast(`🪙 +${AD_REWARD} credits earned!`, 'success');
};

function simulateAd() {
  adSecs = AD_SECS;
  const scr = document.getElementById('adScreen'),
        cd  = document.getElementById('adCd'),
        sk  = document.getElementById('adSkip'),
        an  = document.getElementById('adNote'),
        tx  = document.getElementById('adText');
  scr.classList.add('open');
  tx.textContent='Loading ad...'; cd.style.display='none'; sk.style.display='none';
  if(an) an.style.display='none';
  setTimeout(() => {
    tx.textContent='📺 Ad Playing'; cd.style.display='block';
    if(an) an.style.display='block';
    cd.textContent=adSecs+'s';
    adTimer = setInterval(() => {
      adSecs--; cd.textContent=adSecs+'s';
      if (adSecs <= AD_SECS-5) sk.style.display='block';
      if (adSecs <= 0) { clearInterval(adTimer); adDone(); }
    }, 1000);
  }, 1500);
}

function adDone() {
  clearInterval(adTimer);
  document.getElementById('adScreen').classList.remove('open');
  window.onAdRewarded();
}

// ════════════════════════════════════════════════════════════
//  HELPERS
// ════════════════════════════════════════════════════════════
function openModal(id)  { document.getElementById(id).classList.add('open'); }
function closeModal(id) { document.getElementById(id).classList.remove('open'); }
document.querySelectorAll('.modal-overlay').forEach(el => {
  el.addEventListener('click', e => { if (e.target===el) el.classList.remove('open'); });
});

let tTO;
function showToast(msg, type) {
  const t = document.getElementById('toast');
  t.textContent=msg; t.className='toast show'+(type?' '+type:'');
  clearTimeout(tTO);
  tTO = setTimeout(() => t.classList.remove('show'), 3200);
}

init();
</script>

<!--
╔══════════════════════════════════════════════════════════╗
║  HOW CONVERSION WORKS (no API, no account needed)        ║
╠══════════════════════════════════════════════════════════╣
║  1. User hums → microphone captured via MediaRecorder    ║
║  2. YIN pitch detection finds exact note frequencies     ║
║  3. Notes are grouped into a clean melody sequence       ║
║  4. OfflineAudioContext synthesizes each note with:      ║
║     - Harmonic overtones (makes it sound like real inst) ║
║     - ADSR envelope (attack/decay/sustain/release)       ║
║     - Reverb impulse response (room sound)               ║
║  5. Output rendered to 44.1kHz stereo WAV blob           ║
║  6. User plays it back instantly in the browser          ║
║                                                          ║
║  AD SETUP:                                               ║
║  Web  → Replace ca-pub-XXXXXXXXXXXXXXXX + ad slot IDs   ║
║  App  → Replace AdMob IDs in MainActivity.java          ║
╚══════════════════════════════════════════════════════════╝
-->
</body>
</html>
