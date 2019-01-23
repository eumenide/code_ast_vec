

public class OwnerDrawExample {

	public static void main(String[] args) {

		Display display = new Display();
		Shell shell = new Shell(display, SWT.CLOSE);
		shell.setSize(400, 400);
		shell.setLayout(new GridLayout());

		OwnerDrawExample example = new OwnerDrawExample();
		example.createPartControl(shell);

		shell.open();

		while (!shell.isDisposed()) {
			display.readAndDispatch();
		}
		display.dispose();
	}

	private static int COLUMN_COUNT = 3;

	class CountryEntry {

		String name;

		String cupYear;

		private String baseName;

		CountryEntry(String countryName, String englishName, String worldCupYear) {
			name = countryName;
			cupYear = worldCupYear;
			baseName = englishName;
		}

		public int getHeight(Event event) {
			switch (event.index) {
			case 0:
				return event.gc.textExtent(name).y;
			case 1:
				return 50;
			case 2:
				return event.gc.textExtent(cupYear).y;
			default:
				return 10;
			}
		}

		public int getWidth(Event event) {

			switch (event.index) {
			case 0:
				return event.gc.textExtent(getDisplayString().toString()).x + 50;

			case 1:
				return 200;

			case 2:
				return event.gc.textExtent(cupYear).x + 5;

			default:
				return 10;
			}
		}

		protected void drawFlag(Event event) {
			event.gc.setBackground(viewer.getControl().getDisplay()
					.getSystemColor(SWT.COLOR_BLUE));

			Rectangle bounds = event.getBounds();
			bounds.width += 100;
			event.gc.fillRectangle(bounds);
		}

		private void drawCupYear(Event event) {
			event.gc.drawText(cupYear, event.x, event.y);

		}

		protected void drawName(Event event) {

			StringBuffer buffer = getDisplayString();

			Display display = viewer.getControl().getDisplay();
			TextLayout layout = new TextLayout(display);
			layout.setText(buffer.toString());

			TextStyle plain = new TextStyle(JFaceResources
					.getFont(JFaceResources.DEFAULT_FONT), display
					.getSystemColor(SWT.COLOR_LIST_FOREGROUND), display
					.getSystemColor(SWT.COLOR_LIST_BACKGROUND));

			TextStyle italic = new TextStyle(JFaceResources.getFontRegistry()
					.getItalic(JFaceResources.DEFAULT_FONT), display
					.getSystemColor(SWT.COLOR_BLUE), display
					.getSystemColor(SWT.COLOR_LIST_BACKGROUND));

			layout.setStyle(plain, 0, name.length() - 1);
			layout.setStyle(italic, name.length(), buffer.length() - 1);

			layout.draw(event.gc, event.x, event.y);

		}

		private StringBuffer getDisplayString() {
			StringBuffer buffer = new StringBuffer();
			buffer.append(name);
			buffer.append(" (");
			buffer.append(baseName);
			buffer.append(")");
			return buffer;
		}

		public void draw(Event event) {

			switch (event.index) {
			case 0:
				drawName(event);
				break;
			case 1:
				drawFlag(event);
				break;
			case 2:
				drawCupYear(event);
				break;

			default:
				break;
			}

		}
	}

	private class GermanyEntry extends CountryEntry {

		GermanyEntry() {
			super("Deutschland", "Germany", "1990");
		}

		protected void drawFlag(Event event) {

			Rectangle bounds = event.getBounds();
			bounds.width += 100;
			int stripeHeight = bounds.height / 3;
			Rectangle stripe = new Rectangle(bounds.x, bounds.y, bounds.width,
					stripeHeight);

			event.gc.setBackground(viewer.getControl().getDisplay()
					.getSystemColor(SWT.COLOR_BLACK));
			event.gc.fillRectangle(stripe);

			stripe.y += stripeHeight;

			event.gc.setBackground(viewer.getControl().getDisplay()
					.getSystemColor(SWT.COLOR_YELLOW));
			event.gc.fillRectangle(stripe);

			stripe.y += stripeHeight;

			event.gc.setBackground(viewer.getControl().getDisplay()
					.getSystemColor(SWT.COLOR_RED));
			event.gc.fillRectangle(stripe);

		}

	}

	private class AustriaEntry extends CountryEntry {

		AustriaEntry() {
			super("sterreich", "Austria", "TBD");
		}

		protected void drawFlag(Event event) {

			Rectangle bounds = event.getBounds();
			bounds.width += 100;
			int stripeHeight = bounds.height / 3;
			Rectangle stripe = new Rectangle(bounds.x, bounds.y, bounds.width,
					stripeHeight);

			event.gc.setBackground(viewer.getControl().getDisplay()
					.getSystemColor(SWT.COLOR_RED));
			event.gc.fillRectangle(stripe);

			stripe.y += stripeHeight;

			event.gc.setBackground(viewer.getControl().getDisplay()
					.getSystemColor(SWT.COLOR_WHITE));
			event.gc.fillRectangle(stripe);

			stripe.y += stripeHeight;

			event.gc.setBackground(viewer.getControl().getDisplay()
					.getSystemColor(SWT.COLOR_RED));
			event.gc.fillRectangle(stripe);

		}
	}

	private class EnglandEntry extends CountryEntry {
		EnglandEntry() {
			super("Blighty", "England", "1966");
		}

		protected void drawFlag(Event event) {

			Rectangle bounds = event.getBounds();
			bounds.width += 100;

			event.gc.setBackground(viewer.getControl().getDisplay()
					.getSystemColor(SWT.COLOR_RED));
			event.gc.fillRectangle(new Rectangle(bounds.width / 2 + bounds.x
					- 5, bounds.y, 10, bounds.height));
			event.gc.fillRectangle(new Rectangle(bounds.x, bounds.height / 2
					+ bounds.y - 5, bounds.width, 10));

		}
	}
	
	private TableViewer viewer;

	private CountryEntry[] entries;

	public OwnerDrawExample() {
		entries = new CountryEntry[3];
		entries[0] = new AustriaEntry();
		entries[1] = new GermanyEntry();
		entries[2] = new EnglandEntry();
	}

	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent);

		viewer.setContentProvider(new IStructuredContentProvider() {
			public void dispose() {
			};

			public Object[] getElements(Object inputElement) {
				return entries;
			};

			public void inputChanged(org.eclipse.jface.viewers.Viewer viewer,
					Object oldInput, Object newInput) {
			}

		});
		createColumns();

		viewer.setLabelProvider(new OwnerDrawLabelProvider());
		viewer.setInput(this);

		GridData data = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.GRAB_VERTICAL | GridData.FILL_BOTH);

		viewer.getControl().setLayoutData(data);

		viewer.getTable().addListener(SWT.MeasureItem, new Listener() {
			public void handleEvent(Event event) {
				TableItem item = (TableItem) event.item;
				CountryEntry country = (CountryEntry) item.getData();

				event.setBounds(measure(country, event));
			}
		});

		viewer.getTable().addListener(SWT.PaintItem, new Listener() {
			public void handleEvent(Event event) {
				TableItem item = (TableItem) event.item;
				CountryEntry entry = (CountryEntry) item.getData();
				entry.draw(event);

			}
		});

		viewer.getTable().addListener(SWT.EraseItem, new Listener() {
			public void handleEvent(Event event) {

				Rectangle bounds = event.getBounds();
				if ((event.detail & SWT.SELECTED) > 0) {

					Color oldForeground = event.gc.getForeground();
					Color oldBackground = event.gc.getBackground();

					event.gc.setBackground(viewer.getControl().getDisplay()
							.getSystemColor(SWT.COLOR_LIST_SELECTION));
					event.gc.setForeground(viewer.getControl().getDisplay()
							.getSystemColor(SWT.COLOR_LIST_SELECTION_TEXT));
					event.gc.fillRectangle(bounds);
	protected Rectangle measure(CountryEntry entry, Event event) {
		return new Rectangle(0, 0, entry.getWidth(event), entry
				.getHeight(event));
	}

	private void createColumns() {
		TableLayout layout = new TableLayout();
		viewer.getTable().setLayout(layout);
		viewer.getTable().setHeaderVisible(true);
		viewer.getTable().setLinesVisible(true);

		for (int i = 0; i < COLUMN_COUNT; i++) {
			TableColumn tc = new TableColumn(viewer.getTable(), SWT.NONE, i);
			layout.addColumnData(new ColumnPixelData(100));
			tc.setText(getTitleFor(i));
		}
		;
	}

	private String getTitleFor(int i) {
		switch (i) {
		case 0:
			return "Name";
		case 1:
			return "Flag";
		case 2:
			return "World Cup Year";
		}
		return "Unknown";
	}

	public void setFocus() {

	}

}
