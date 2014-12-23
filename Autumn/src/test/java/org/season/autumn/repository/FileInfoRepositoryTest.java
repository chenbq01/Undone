package org.season.autumn.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.season.autumn.domain.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/root-context.xml",
		"classpath:/META-INF/spring/jms/jms-activemq.xml",
		"classpath:/META-INF/spring/data/data-jpa.xml", })
@TransactionConfiguration(defaultRollback = false)
public class FileInfoRepositoryTest {

	@Autowired
	private FileInfoRepository fileInfoRepository;

	@Test
	@Transactional
	public void testFindByUsernameInOneSession() {
		FileInfo fileInfo1 = fileInfoRepository.findByFilename("爱洋葱系统.pptx");
		FileInfo fileInfo2 = fileInfoRepository.findByFilename("爱洋葱系统.pptx");
		Assert.assertSame(fileInfo1, fileInfo2);
	}

	@Test
	public void testFindByUsernameInMultiSession() {
		FileInfo fileInfo1 = fileInfoRepository.findByFilename("爱洋葱系统.pptx");
		System.out.println("+++++第一次查询结束+++++");
		FileInfo fileInfo2 = fileInfoRepository.findByFilename("爱洋葱系统.pptx");
		System.out.println("+++++第二次查询结束+++++");
		Assert.assertSame(fileInfo1, fileInfo2);
	}

	@Test
	@Transactional
	public void testFindOneInOneSession() {
		FileInfo fileInfo1 = fileInfoRepository.findOne(Long.valueOf("1"));
		FileInfo fileInfo2 = fileInfoRepository.findOne(Long.valueOf("1"));
		Assert.assertSame(fileInfo1, fileInfo2);
	}

	@Test
	public void testFindOneInMultiSession() {
		FileInfo fileInfo1 = fileInfoRepository.findOne(Long.valueOf("1"));
		System.out.println("+++++第一次查询结束+++++");
		FileInfo fileInfo2 = fileInfoRepository.findOne(Long.valueOf("1"));
		System.out.println("+++++第二次查询结束+++++");
		Assert.assertSame(fileInfo1, fileInfo2);
	}

}
