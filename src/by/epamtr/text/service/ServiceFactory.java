package by.epamtr.text.service;

import by.epamtr.text.service.impl.TextProcessorServiceImpl;
import by.epamtr.text.service.impl.TextServiceImpl;

public final class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();

	private final TextService textService = new TextServiceImpl();
	private final TextProcessorService textProcessorService = new TextProcessorServiceImpl();

	private ServiceFactory() {
	}

	public TextService getTextService() {

		return textService;
	}

	public TextProcessorService getTextProcessorService() {
		return textProcessorService;
	}

	public static ServiceFactory getInstance() {
		return instance;
	}

}
