package com.sky.f1.batch.watcher;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sky.f1.batch.config.PropertyLoader;
import com.sky.f1.batch.process.IProcessDriverData;
import com.sky.f1.batch.process.ProcessDriverData;

public class FileWatcher implements Runnable {

	private final File folder;
	private static final List<WatchService> watchServices = new ArrayList<>();
	private IProcessDriverData processDriverData=new ProcessDriverData();
	public FileWatcher() {
		File folder = new File(PropertyLoader.getInstance().getProperty("input.folder"));
		this.folder=folder;
	}
	public void watch() {
		if (folder.exists()) {
			Thread thread = new Thread(this);
			thread.setDaemon(true);
			thread.start();
		}
	}
	@Override
	public void run() {
		try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
			
			Path path = Paths.get(folder.getAbsolutePath());
			path.register(watchService, ENTRY_CREATE);
			watchServices.add(watchService);
			boolean poll = true;
			while (poll) {
				poll = pollEvents(watchService);
			}
		} catch (Exception e) {
			Thread.currentThread().interrupt();
		}
	}
	protected boolean pollEvents(WatchService watchService) throws Exception {
		WatchKey key = watchService.take();
		Path path = (Path) key.watchable();
		for (WatchEvent<?> event : key.pollEvents()) {
			if (event.kind() == ENTRY_CREATE) {
				File file = path.resolve((Path) event.context()).toFile();
				FileEvent fileEvent = new FileEvent(file);
				String filename=fileEvent.getFile().getName();
				processDriverData.processFile(filename);
			}
		}
		return key.reset();
	}

	
	public static List<WatchService> getWatchServices() {
		return Collections.unmodifiableList(watchServices);
	}

}
